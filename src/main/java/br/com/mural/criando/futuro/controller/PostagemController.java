package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.service.PageService;
import br.com.mural.criando.futuro.service.PostagemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostagemController {

    private final PostagemService postagemService;

    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/criarPostagem")
    public String criarPostagem() {
        return "criarPostagem";
    }

    @PostMapping("/postar-postagem")
    public String postarNoticia(@RequestParam("titulo") String titulo,
                                @RequestParam("autor") String autor,
                                @RequestParam("postagem") String texto,
                                @RequestParam(value = "imagens", required = false) MultipartFile[] imagens) {
        postagemService.criarNovaPostagem(titulo, autor, texto, imagens);
        return "redirect:/criarPostagem";
    }
}

