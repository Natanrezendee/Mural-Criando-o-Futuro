package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.model.noticia.Noticia;
import br.com.mural.criando.futuro.service.NoticiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticiaController {

    private final NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping("/noticias")
    public List<Noticia> getNoticias() {
        return noticiaService.getAllNoticias();
    }

    @PostMapping("/noticia")
    public void salvarNoticia(@RequestBody Noticia noticia) {
        noticiaService.saveNoticia(noticia);
    }

    @DeleteMapping("/noticia/{id}")
    public void deletarNoticia(@PathVariable(value = "id") Long id) {
        noticiaService.deleteNoticia(id);
    }

}
