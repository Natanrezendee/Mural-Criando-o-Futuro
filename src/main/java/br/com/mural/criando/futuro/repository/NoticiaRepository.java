package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.noticia.Noticia;
import br.com.mural.criando.futuro.model.noticia.NoticiaAbreviada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    @Query("SELECT new br.com.mural.criando.futuro.model.noticia.NoticiaAbreviada(" +
            "n.id, n.titulo, " +
            "CASE WHEN LENGTH(n.texto) > 100 THEN CONCAT(SUBSTRING(n.texto, 1, 100), '...') ELSE n.texto END) " +
            "FROM Noticia n")
    Page<NoticiaAbreviada> findAllNoticiasAbreviadas(Pageable pageable);
    @Query("SELECT n.imagens FROM Noticia n WHERE n.id = :id")
    List<String> findImagensByNoticiaId(Long id);

}
