package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.cardapio.Dia;
import br.com.mural.criando.futuro.model.cardapio.Semana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long> {
    List<Dia> findBySemana(Semana semana);
}
