package br.com.mural.criando.futuro.model.noticia;


import lombok.Data;

@Data
public class NoticiaAbreviada {
    private Long id;
    private String titulo;
    private String textoAbreviado;
    private String imagem;

    public NoticiaAbreviada(Long id, String titulo, String textoAbreviado) {
        this.id = id;
        this.titulo = titulo;
        this.textoAbreviado = textoAbreviado;
    }
}
