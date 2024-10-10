package com.ien.ienapp.service;

import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.entity.MateriaTemaId;
import com.ien.ienapp.repository.MateriaTemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaTemaService {
    @Autowired
    private MateriaTemaRepository materiaTemaRepository;


    public List<MateriaTema> getAllMateriasTemas() {
        return materiaTemaRepository.findAll();
    }

    public MateriaTema getMateriaTemaById(MateriaTemaId id) {
        return materiaTemaRepository.findById(id).orElse(null);
    }

    public MateriaTema createMateriaTema(MateriaTema materiaTema) {
        // Guardar en la base de datos
        return materiaTemaRepository.save(materiaTema);
    }

    public void deleteMateriaTema(MateriaTemaId id) {
        materiaTemaRepository.deleteById(id);
    }
}
