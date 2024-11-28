package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.model.noticia.NoticiaAbreviada;
import br.com.mural.criando.futuro.service.PageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;


@Controller
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "2") int size,
                        Model model) {
        Page<NoticiaAbreviada> noticias = pageService.getAllNoticiasAbreviadas(page, size);
        model.addAttribute("noticias", noticias.getContent());
        model.addAttribute("paginaAtual", page);
        model.addAttribute("totalPaginas", noticias.getSize());
        return "index";
    }

    @GetMapping("/noticia/{id}")
    public String noticia(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("noticia", Objects.requireNonNull(pageService.getNoticiaById(id).orElse(null)));
        return "noticia";
    }

    @GetMapping("/informacoes")
    public String informacoes() {
        return "informacoes";
    }

    @GetMapping("/contato")
    public String contato() {
        return "contato";
    }
}
