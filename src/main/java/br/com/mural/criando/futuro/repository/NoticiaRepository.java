package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
}
