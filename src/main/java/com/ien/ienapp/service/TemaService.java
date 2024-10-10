package com.ien.ienapp.service;

import com.ien.ienapp.entity.Tema;
import com.ien.ienapp.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {
    @Autowired
    private TemaRepository temaRepository;

    public List<Tema> getAllTemas() {
        return temaRepository.findAll();
    }

    public Tema getTemaById(Integer id) {
        return temaRepository.findById(id).orElse(null);
    }

    public Tema createTema(Tema tema) {
        return temaRepository.save(tema);
    }

    public Tema updateTema(Integer id, Tema tema) {
        if (temaRepository.existsById(id)) {
            tema.setId(id);
            return temaRepository.save(tema);
        }
        return null;
    }

    public void deleteTema(Integer id) {
        temaRepository.deleteById(id);
    }
}
