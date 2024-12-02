package br.com.mural.criando.futuro.model.cardapio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Semana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SemanaTipo tipo; // A, B, C ou D

    @OneToMany(mappedBy = "semana")
    private List<Dia> dias;
}

