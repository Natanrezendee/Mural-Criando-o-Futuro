package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.service.NoticiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {

    private final NoticiaService noticiaService;

    public PageController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("noticias", noticiaService.getAllNoticias());
        return "inicio";
    }

    // Página de Notícia - Exibe a notícia completa
    @GetMapping("/noticia/{id}")
    public String noticia(@PathVariable("id") Long id, Model model) {
        model.addAttribute("noticia", noticiaService.getNoticiaById(id).orElse(null));
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
