package br.com.mural.criando.futuro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String texto;
    private String autor;
    @Transient
    private String textoAbreviado;

    @ElementCollection
    @CollectionTable(
            name = "noticia_imagens",
            joinColumns = @JoinColumn(name = "noticia_id")
    )
    @Column(name = "url_imagem")
    private List<String> imagens;

    public String getTextoAbreviado() {
        if (texto != null && texto.length() > 100) {
            return texto.substring(0, 100) + "...";
        }
        return texto;
    }
}
