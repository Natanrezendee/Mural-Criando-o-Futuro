package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.cardapio.Semana;
import br.com.mural.criando.futuro.model.cardapio.SemanaTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemanaRepository extends JpaRepository<Semana, Long> {
    Optional<Semana> findByTipo(SemanaTipo semanaAtual);
}
