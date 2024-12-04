package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.postagem.Postagem;
import br.com.mural.criando.futuro.model.postagem.PostagemAbreviada;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class PageService {

    private final PostagemService postagemService;
    private final SemanaService semanaService;


    public PageService(PostagemService postagemService, SemanaService semanaService) {
        this.postagemService = postagemService;
        this.semanaService = semanaService;
    }

    public String carregarDadosIndex(int pagina, int tamanho, Model model) {
        Page<PostagemAbreviada> postagensAbreviadas = postagemService.getPostagensAbreviadasPage(pagina, tamanho);
        model.addAttribute("postagens", postagensAbreviadas.getContent());
        model.addAttribute("paginaAtual", pagina);
        model.addAttribute("totalPaginas", postagensAbreviadas.getSize());
        return "principais/index";
    }


    public String carregarDadosPostagem(Long id, Model model) {
        Optional<Postagem> postagem = postagemService.getPostagemById(id);
        postagem.ifPresent(value -> model.addAttribute("postagem", value));
        model.addAttribute("ultimasPostagens", postagemService.getUltimasPostagens(4));
        return "principais/postagem";
    }

    public String carregarDadosCardapio(Model model) {
        model.addAttribute("semanaAtual", semanaService.getSemanaAtual());
        return "principais/cardapio";
    }
}
