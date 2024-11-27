package br.com.mural.criando.futuro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String periodo; // "Manhã", "Almoço", "Tarde"

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    private String comidas; // String contendo as comidas disponíveis

}

