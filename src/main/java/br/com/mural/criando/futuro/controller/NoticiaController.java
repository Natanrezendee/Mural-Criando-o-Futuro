package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.model.Noticia;
import br.com.mural.criando.futuro.service.ImgurService;
import br.com.mural.criando.futuro.service.NoticiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class NoticiaController {

    private final NoticiaService noticiaService;
    private final ImgurService imgurService;


    public NoticiaController(NoticiaService noticiaService, ImgurService imgurService) {
        this.noticiaService = noticiaService;
        this.imgurService = imgurService;
    }

    @GetMapping("/noticias")
    public List<Noticia> getNoticias() {
        return noticiaService.getAllNoticias();
    }

    @PostMapping("/noticia")
    public void salvarNoticia(@RequestBody Noticia noticia) {
        noticiaService.saveNoticia(noticia);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imgurService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload da imagem.");
        }
    }


}
