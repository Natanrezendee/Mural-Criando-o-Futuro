package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.cardapio.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {
}
