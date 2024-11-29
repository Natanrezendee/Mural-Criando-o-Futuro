package br.com.mural.criando.futuro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostagemController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/criarPostagem")
    public String criarPostagem() {
        return "criarPostagem";
    }
}

