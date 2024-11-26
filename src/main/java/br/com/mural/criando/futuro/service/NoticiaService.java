package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.Noticia;

import java.util.List;
import java.util.Optional;

public interface NoticiaService {
    List<Noticia> getAllNoticias();
    Optional<Noticia> getNoticiaById(Long id);
    void saveNoticia(Noticia noticia);
}
