package br.com.mural.criando.futuro.controller;

import br.com.mural.criando.futuro.model.cardapio.Dia;
import br.com.mural.criando.futuro.model.cardapio.Semana;
import br.com.mural.criando.futuro.service.CardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping("/semana-atual")
    public ResponseEntity<Semana> getSemanaAtual() {
        Semana semana = cardapioService.getSemanaAtual();
        return ResponseEntity.ok(semana);
    }

    @GetMapping("/cardapios-da-semana")
    public ResponseEntity<List<Dia>> getCardapiosDaSemana() {
        Semana semana = cardapioService.getSemanaAtual();
        List<Dia> dias = cardapioService.getCardapiosDaSemana(semana);
        return ResponseEntity.ok(dias);
    }
}
