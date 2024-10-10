package com.ien.ienapp.service;

import com.ien.ienapp.entity.EstadoCivil;
import com.ien.ienapp.repository.EstadoCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoCivilService {

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    public List<EstadoCivil> getAllEstadoCivil() {
        return estadoCivilRepository.findAll();
    }

    public Optional<EstadoCivil> getEstadoCivilById(Integer id) {
        return estadoCivilRepository.findById(id);
    }

    public EstadoCivil createEstadoCivil(EstadoCivil estadoCivil) {
        return estadoCivilRepository.save(estadoCivil);
    }

    public void deleteEstadoCivil(Integer id) {
        estadoCivilRepository.deleteById(id);
    }
}