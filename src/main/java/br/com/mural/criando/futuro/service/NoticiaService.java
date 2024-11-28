package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.noticia.Noticia;
import br.com.mural.criando.futuro.model.noticia.NoticiaAbreviada;
import br.com.mural.criando.futuro.repository.NoticiaRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private final Cache<String, Page<NoticiaAbreviada>> cacheNoticiasAbreviadas;


    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
        this.cacheNoticiasAbreviadas = Caffeine.newBuilder()
                .expireAfterWrite(7, TimeUnit.DAYS)
                .maximumSize(1000)
                .build();
    }

    public List<Noticia> getAllNoticias() {
        return noticiaRepository.findAll();
    }


    public void saveNoticia(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    public void deleteNoticia(Long id) {
        noticiaRepository.deleteById(id);
        clearCache();
    }

    public Page<NoticiaAbreviada> getNoticiasAbreviadasPage(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);

        String cacheKey = "noticias_page_" + pagina + "_size_" + tamanho;
        Page<NoticiaAbreviada> noticiasAbreviadas = cacheNoticiasAbreviadas.getIfPresent(cacheKey);

        if (noticiasAbreviadas == null) {
            noticiasAbreviadas = noticiaRepository.findAllNoticiasAbreviadas(pageable);
            carregarImagensParaNoticias(noticiasAbreviadas.getContent());
            cacheNoticiasAbreviadas.put(cacheKey, noticiasAbreviadas);
        }
        return noticiasAbreviadas;
    }

    public Optional<Noticia> getNoticiaById(Long id) {
        return noticiaRepository.findById(id);
    }

    private void carregarImagensParaNoticias(List<NoticiaAbreviada> noticiasAbreviadas) {
        for (NoticiaAbreviada noticia : noticiasAbreviadas) {
            List<String> imagens = noticiaRepository.findImagensByNoticiaId(noticia.getId());
            if (!imagens.isEmpty()) {
                noticia.setImagem(imagens.get(0));
            }
        }
    }

    private void clearCache() {
        cacheNoticiasAbreviadas.invalidate("noticias");
    }

}
