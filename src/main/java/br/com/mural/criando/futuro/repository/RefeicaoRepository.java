package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.cardapio.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
}
