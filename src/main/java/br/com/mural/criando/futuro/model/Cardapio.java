package br.com.mural.criando.futuro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "Parcial" ou "Integral"

    @ManyToOne
    @JoinColumn(name = "dia_id")
    private Dia dia;

    @OneToMany(mappedBy = "cardapio")
    private List<Horario> horarios;

    // Getters e Setters
}
