package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.postagem.Postagem;
import br.com.mural.criando.futuro.model.postagem.PostagemAbreviada;
import br.com.mural.criando.futuro.model.postagem.PostagemTitulo;
import br.com.mural.criando.futuro.repository.PostagemRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;
    private final ImgurService imgurService;
    private final Cache<String, Page<PostagemAbreviada>> cachePostagensAbreviadas;


    public PostagemService(PostagemRepository postagemRepository, ImgurService imgurService) {
        this.postagemRepository = postagemRepository;
        this.imgurService = imgurService;
        this.cachePostagensAbreviadas = Caffeine.newBuilder()
                .expireAfterWrite(7, TimeUnit.DAYS)
                .maximumSize(1000)
                .build();
    }

    public List<Postagem> getAllPostagem() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> getPostagemById(Long id) {
        return postagemRepository.findById(id);
    }


    public void deletePostagem(Long id) {
        postagemRepository.deleteById(id);
        clearCache();
    }

    public Page<PostagemAbreviada> getPostagensAbreviadasPage(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);

        String cacheKey = "postagens_page_" + pagina + "_size_" + tamanho;
        Page<PostagemAbreviada> postagensAbreviadas = cachePostagensAbreviadas.getIfPresent(cacheKey);

        if (postagensAbreviadas == null) {
            postagensAbreviadas = postagemRepository.findAllPostagensAbreviadas(pageable);
            carregarImagensParaPostagens(postagensAbreviadas.getContent());
            cachePostagensAbreviadas.put(cacheKey, postagensAbreviadas);
        }
        return postagensAbreviadas;
    }

    public List<PostagemTitulo> getUltimasPostagens(int limite) {
        Pageable pageable = PageRequest.of(0, limite, Sort.by(Sort.Order.desc("dataPublicacao")));
        return postagemRepository.findAllByOrderByDataPublicacaoDesc(pageable);
    }

    private void carregarImagensParaPostagens(List<PostagemAbreviada> postagensAbreviadas) {
        for (PostagemAbreviada postagem : postagensAbreviadas) {
            List<String> imagens = postagemRepository.findImagensByPostagemId(postagem.getId());
            if (!imagens.isEmpty()) {
                postagem.setImagem(imagens.get(0));
            }
        }
    }

    private void clearCache() {
        cachePostagensAbreviadas.invalidate("postagens");
    }

    public void criarNovaPostagem(String titulo, String autor, String texto, MultipartFile[] imagens) {
        try{
            Postagem postagem = new Postagem(titulo, texto, autor);
            if (imagens != null && imagens.length > 0) {
                List<String> urlsImagens = new ArrayList<>();
                for (MultipartFile imagem : imagens) {
                    String url = imgurService.uploadImage(imagem);
                    urlsImagens.add(url);
                }
                postagem.setImagens(urlsImagens);
            }
            postagemRepository.save(postagem);
        } catch (Exception ignored) {

        }


    }
}
