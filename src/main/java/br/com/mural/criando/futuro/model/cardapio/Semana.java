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

    private String nome; // A, B, C, D, etc.

    @OneToMany(mappedBy = "semana")
    private List<Dia> dias;

}
