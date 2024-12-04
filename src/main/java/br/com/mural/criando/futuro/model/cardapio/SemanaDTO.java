package br.com.mural.criando.futuro.model.cardapio;

import lombok.Data;

@Data
public class SemanaDTO {
    private Semana semana;
    private String inicioSemana;
    private String fimSemana;

    public SemanaDTO(Semana semana, String inicioSemana, String fimSemana) {
        this.semana = semana;
        this.inicioSemana = inicioSemana;
        this.fimSemana = fimSemana;
    }
}
