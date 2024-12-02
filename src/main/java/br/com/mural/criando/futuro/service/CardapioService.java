package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.cardapio.Dia;
import br.com.mural.criando.futuro.model.cardapio.Semana;
import br.com.mural.criando.futuro.model.cardapio.SemanaTipo;
import br.com.mural.criando.futuro.repository.DiaRepository;
import br.com.mural.criando.futuro.repository.SemanaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardapioService {

    private final SemanaRepository semanaRepository;

    private final DiaRepository diaRepository;

    public CardapioService(SemanaRepository semanaRepository, DiaRepository diaRepository) {
        this.semanaRepository = semanaRepository;
        this.diaRepository = diaRepository;
    }

    public Semana getSemanaAtual() {
        LocalDate dataAtual = LocalDate.now();
        int semanaNumero = (dataAtual.getDayOfYear() - 1) / 7; // Calcula o número da semana no ano
        SemanaTipo semanaAtual = SemanaTipo.values()[semanaNumero % 4]; // Ciclo de 4 semanas

        return semanaRepository.findByTipo(semanaAtual)
                .orElseThrow(() -> new RuntimeException("Semana não encontrada"));
    }

    public List<Dia> getCardapiosDaSemana(Semana semana) {
        return diaRepository.findBySemana(semana);
    }
}

