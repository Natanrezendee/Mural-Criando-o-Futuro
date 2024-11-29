package br.com.mural.criando.futuro.model.postagem;


import lombok.Data;

@Data
public class PostagemAbreviada {
    private Long id;
    private String titulo;
    private String textoAbreviado;
    private String imagem;

    public PostagemAbreviada(Long id, String titulo, String textoAbreviado) {
        this.id = id;
        this.titulo = titulo;
        this.textoAbreviado = textoAbreviado;
    }
}
