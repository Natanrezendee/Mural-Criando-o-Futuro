package br.com.mural.criando.futuro.model.cardapio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRefeicao tipo; // Café da manhã, Almoço, Lanche da tarde

    private String descricao; // Descrição dos alimentos

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;
}

