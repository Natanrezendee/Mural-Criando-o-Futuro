package br.com.mural.criando.futuro.model.cardapio;

import br.com.mural.criando.futuro.model.cardapio.enums.TipoCardapio;
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
    private TipoCardapio tipo;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Refeicao> refeicoes;
}

