package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.service.PostagemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/gerenciar-postagens")
    public String gerenciarPostagens() {
        return "postagens/gerenciarPostagens";
    }

    @GetMapping("/criar-postagem")
    public String criarPostagem() {
        return "postagens/criarPostagem";
    }

    @PostMapping("/postar-postagem")
    public String postarNoticia(@RequestParam("titulo") String titulo,
                                @RequestParam("autor") String autor,
                                @RequestParam("postagem") String texto,
                                @RequestParam(value = "imagens", required = false) MultipartFile[] imagens) {
        postagemService.criarNovaPostagem(titulo, autor, texto, imagens);
        return "redirect:/";
    }

    @GetMapping("/excluir-postagens")
    public String excluirPostagem(Model model) {
        model.addAttribute("postagens",postagemService.getAllPostagensAbreviadas());
        return "postagens/excluirPostagens";
    }

    @PostMapping("/excluir-postagem/{id}")
    public String excluirPostagem(@PathVariable long id) {
        postagemService.deletePostagem(id);
        return "redirect:/excluir-postagens";
    }
}

