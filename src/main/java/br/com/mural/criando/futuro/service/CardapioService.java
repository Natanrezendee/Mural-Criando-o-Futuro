package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.cardapio.*;
import br.com.mural.criando.futuro.model.cardapio.enums.SemanaTipo;
import br.com.mural.criando.futuro.repository.DiaRepository;
import br.com.mural.criando.futuro.repository.SemanaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CardapioService {

    private final SemanaRepository semanaRepository;

    private final DiaRepository diaRepository;

    public CardapioService(SemanaRepository semanaRepository, DiaRepository diaRepository) {
        this.semanaRepository = semanaRepository;
        this.diaRepository = diaRepository;
    }

    public SemanaDTO getSemanaAtual() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate inicioSemana = dataAtual.with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate fimSemana = inicioSemana.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SemanaTipo semanaAtual = SemanaTipo.values()[(dataAtual.getDayOfYear() - 1) / 7 % 4];

        String inicioFormatado = inicioSemana.format(formatter);
        String fimFormatado = fimSemana.format(formatter);
        Semana semana = semanaRepository.findByTipo(semanaAtual).get();
        return new SemanaDTO(semana, inicioFormatado, fimFormatado);
    }

    public List<Dia> getCardapiosDaSemana(Semana semana) {
        return diaRepository.findBySemana(semana);
    }
}

