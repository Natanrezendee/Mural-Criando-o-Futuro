package br.com.mural.criando.futuro.model.cardapio;

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

    @ManyToOne
    @JoinColumn(name = "semana_id")
    private Semana semana;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @OneToOne
    @JoinColumn(name = "cardapio_parcial_id")
    private Cardapio cardapioParcial;

    @OneToOne
    @JoinColumn(name = "cardapio_integral_id")
    private Cardapio cardapioIntegral;
}

