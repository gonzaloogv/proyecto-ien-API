package com.ien.ienapp.service;

import com.ien.ienapp.entity.Paises;
import com.ien.ienapp.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PaisesService {

    @Autowired
    private PaisesRepository paisesRepository;

    public List<Paises> getAllPaises() {
        return paisesRepository.findAll();
    }

    public Optional<Paises> getPaisById(Long id) {
        return paisesRepository.findById(id);
    }

    public Paises createPais(Paises pais) {
        return paisesRepository.save(pais);
    }

    public Paises updatePais(Long id, Paises pais) {
        return paisesRepository.findById(id)
                .map(existingPais -> {
                    existingPais.setDePais(pais.getDePais());
                    existingPais.setFeModificacion(pais.getFeModificacion());
                    return paisesRepository.save(existingPais);
                })
                .orElseThrow(() -> new EntityNotFoundException("País no encontrado con id: " + id));
    }

    public void deletePais(Long id) {
        if (!paisesRepository.existsById(id)) {
            throw new EntityNotFoundException("País no encontrado con id: " + id);
        }
        paisesRepository.deleteById(id);
    }
}
