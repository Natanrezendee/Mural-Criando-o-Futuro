package br.com.mural.criando.futuro.controller;


import br.com.mural.criando.futuro.service.ImgurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImgurController {
    private final ImgurService imgurService;

    public ImgurController(ImgurService imgurService) {
        this.imgurService = imgurService;
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
