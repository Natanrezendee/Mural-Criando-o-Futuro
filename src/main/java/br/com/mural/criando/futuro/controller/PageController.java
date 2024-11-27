package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.service.NoticiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;


@Controller
public class PageController {

    private final NoticiaService noticiaService;

    public PageController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("noticias", noticiaService.getAllNoticias());
        return "index";
    }

    @GetMapping("/noticia/{id}")
    public String noticia(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("noticia", Objects.requireNonNull(noticiaService.getNoticiaById(id).orElse(null)));
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
