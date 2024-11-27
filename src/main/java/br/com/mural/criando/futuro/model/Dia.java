package br.com.mural.criando.futuro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: Segunda-feira, Terça-feira, etc.

    @ManyToOne
    @JoinColumn(name = "semana_id")
    private Semana semana;

    @OneToMany(mappedBy = "dia")
    private List<Cardapio> cardapios;

}
