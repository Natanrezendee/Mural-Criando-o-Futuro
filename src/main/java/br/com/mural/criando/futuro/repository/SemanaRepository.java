package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.Semana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemanaRepository extends JpaRepository<Semana, Long> {
}
