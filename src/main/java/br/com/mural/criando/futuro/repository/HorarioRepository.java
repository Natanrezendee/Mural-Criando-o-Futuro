package br.com.mural.criando.futuro.repository;

import br.com.mural.criando.futuro.model.cardapio.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
