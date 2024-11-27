package br.com.mural.criando.futuro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // Campo para armazenar a data de publicação
    private LocalDate dataPublicacao;

    @Transient
    private String textoAbreviado;

    @ElementCollection
    @CollectionTable(
            name = "noticia_imagens",
            joinColumns = @JoinColumn(name = "noticia_id")
    )
    @Column(name = "url_imagem")
    @Cascade(CascadeType.REMOVE)
    private List<String> imagens;

    public String getTextoAbreviado() {
        if (texto != null && texto.length() > 100) {
            return texto.substring(0, 100) + "...";
        }
        return texto;
    }

    public String getDataPublicacaoFormatada() {
        if (dataPublicacao != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            return dataPublicacao.format(formatter);
        }
        return "";
    }

    @PrePersist
    public void prePersist() {
        if (dataPublicacao == null) {
            dataPublicacao = LocalDate.now();
        }
    }
}
