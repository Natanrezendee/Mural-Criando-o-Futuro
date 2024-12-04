package br.com.mural.criando.futuro.model.postagem;


import lombok.Data;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

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

    public String getTextoAbreviadoComoHtml() {
        if (this.textoAbreviado != null && !this.textoAbreviado.isEmpty()) {
            Parser parser = Parser.builder().build();
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            return renderer.render(parser.parse(this.textoAbreviado));
        }
        return "";
    }
}
