package com.ien.ienapp.service;

import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.repository.PlanesEstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanesEstudiosService {

    @Autowired
    private PlanesEstudiosRepository planesEstudiosRepository;

    public List<PlanesEstudios> getAllPlanesEstudios() {
        return planesEstudiosRepository.findAll();
    }

    public Optional<PlanesEstudios> getPlanesEstudiosById(Integer id) {
        return planesEstudiosRepository.findById(id);
    }

    public PlanesEstudios createPlanesEstudios(PlanesEstudios planesEstudios) {
        return planesEstudiosRepository.save(planesEstudios);
    }

    public void deletePlanesEstudios(Integer id) {
        planesEstudiosRepository.deleteById(id);
    }
}
