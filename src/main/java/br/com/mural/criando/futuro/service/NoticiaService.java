package br.com.mural.criando.futuro.service;

import br.com.mural.criando.futuro.model.noticia.Noticia;
import br.com.mural.criando.futuro.model.noticia.NoticiaAbreviada;
import br.com.mural.criando.futuro.repository.NoticiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public List<Noticia> getAllNoticias() {
        return noticiaRepository.findAll();
    }


    public void saveNoticia(Noticia noticia) {
        noticiaRepository.save(noticia);
    }

    public void deleteNoticia(Long id) {
        noticiaRepository.deleteById(id);
    }
}
