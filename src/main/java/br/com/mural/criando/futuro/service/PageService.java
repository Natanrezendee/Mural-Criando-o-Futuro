package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.noticia.Noticia;
import br.com.mural.criando.futuro.model.noticia.NoticiaAbreviada;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class PageService {

    private final NoticiaService noticiaService;


    public PageService(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    public String carregarDadosIndex(int pagina, int tamanho, Model model) {
        Page<NoticiaAbreviada> noticiasAbreviadas = noticiaService.getNoticiasAbreviadasPage(pagina, tamanho);
        model.addAttribute("noticias", noticiasAbreviadas.getContent());
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("totalPaginas", noticiasAbreviadas.getSize());
        return "index";
    }


    public String carregarDadosNoticia(Long id, Model model) {
        Optional<Noticia> noticia = noticiaService.getNoticiaById(id);
        noticia.ifPresent(value -> model.addAttribute("noticia", value));
        model.addAttribute("ultimasPostagens", noticiaService.getUltimasPostagens(4));
        return "noticia";
    }

}
