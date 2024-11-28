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
public class PageService {
    private final NoticiaRepository noticiaRepository;

    private final Cache<String, Page<NoticiaAbreviada>> cacheNoticiasAbreviadas;


    public PageService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
        this.cacheNoticiasAbreviadas = Caffeine.newBuilder()
                .expireAfterWrite(7, TimeUnit.DAYS)
                .maximumSize(1000)
                .build();
    }

    public Page<NoticiaAbreviada> getAllNoticiasAbreviadas(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);

        String cacheKey = "noticias_page_" + pagina + "_size_" + tamanho;
        Page<NoticiaAbreviada> noticiasAbreviadasPage = cacheNoticiasAbreviadas.getIfPresent(cacheKey);

        if (noticiasAbreviadasPage == null) {
            noticiasAbreviadasPage = noticiaRepository.findAllNoticiasAbreviadas(pageable);
            carregarImagensParaNoticias(noticiasAbreviadasPage.getContent());
            cacheNoticiasAbreviadas.put(cacheKey, noticiasAbreviadasPage);
        }

        return noticiasAbreviadasPage;
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

    public void invalidateCache() {
        cacheNoticiasAbreviadas.invalidate("noticias");
    }
}
