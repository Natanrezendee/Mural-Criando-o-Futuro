package br.com.mural.criando.futuro.model.cardapio;

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

    @Enumerated(EnumType.STRING)
    private TipoCardapio tipo; // Parcial ou Integral

    @OneToMany(mappedBy = "cardapio")
    private List<Refeicao> refeicoes;
}

