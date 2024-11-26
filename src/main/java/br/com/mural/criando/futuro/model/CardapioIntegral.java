package br.com.mural.criando.futuro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CardapioIntegral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
