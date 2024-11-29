package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.postagem.Postagem;
import br.com.mural.criando.futuro.model.postagem.PostagemAbreviada;
import br.com.mural.criando.futuro.model.postagem.PostagemTitulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    @Query("SELECT new br.com.mural.criando.futuro.model.postagem.PostagemAbreviada(" +
            "n.id, n.titulo, " +
            "CASE WHEN LENGTH(n.texto) > 100 THEN CONCAT(SUBSTRING(n.texto, 1, 150), '...') ELSE n.texto END) " +
            "FROM Postagem n " +
            "ORDER BY n.dataPublicacao DESC")
    Page<PostagemAbreviada> findAllPostagensAbreviadas(Pageable pageable);
    @Query("SELECT n.imagens FROM Postagem n WHERE n.id = :id")
    List<String> findImagensByPostagemId(Long id);
    List<PostagemTitulo> findAllByOrderByDataPublicacaoDesc(Pageable pageable);
}
