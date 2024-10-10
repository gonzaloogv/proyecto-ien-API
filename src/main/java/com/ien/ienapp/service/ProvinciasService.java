package com.ien.ienapp.service;

import com.ien.ienapp.entity.Provincias;
import com.ien.ienapp.repository.ProvinciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciasService {

    @Autowired
    private ProvinciasRepository provinciasRepository;

    public List<Provincias> getAllProvincias() {
        return provinciasRepository.findAll();
    }

    public Optional<Provincias> getProvinciaById(Integer id) {
        return provinciasRepository.findById(id);
    }

    public Provincias createProvincia(Provincias provincia) {
        return provinciasRepository.save(provincia);
    }

    public Provincias updateProvincia(Integer id, Provincias provincia) {
        Optional<Provincias> existingProvincia = provinciasRepository.findById(id);
        if (existingProvincia.isPresent()) {
            Provincias updatedProvincia = existingProvincia.get();
            updatedProvincia.setDeProvincia(provincia.getDeProvincia());
            updatedProvincia.setPais(provincia.getPais());
            updatedProvincia.setFeModificacion(provincia.getFeModificacion());
            return provinciasRepository.save(updatedProvincia);
        }
        return null;
    }

    public void deleteProvincia(Integer id) {
        provinciasRepository.deleteById(id);
    }
}
