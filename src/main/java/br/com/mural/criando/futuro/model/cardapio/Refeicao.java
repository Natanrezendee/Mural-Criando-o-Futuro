package br.com.mural.criando.futuro.model.cardapio;

import br.com.mural.criando.futuro.model.cardapio.enums.TipoRefeicao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRefeicao tipo;

    private String alimentos;

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    private Cardapio cardapio;

    public Refeicao(TipoRefeicao tipoRefeicao, String alimentos) {
        this.tipo = tipoRefeicao;
        this.alimentos = alimentos;
    }
}

