package com.ien.ienapp.service;

import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    public Materia getMateriaById(Integer id) {
        return materiaRepository.findById(id).orElse(null);
    }

    public Materia createMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    public Materia updateMateria(Integer id, Materia materia) {
        if (materiaRepository.existsById(id)) {
            materia.setId(id);
            return materiaRepository.save(materia);
        }
        return null;
    }

    public void deleteMateria(Integer id) {
        materiaRepository.deleteById(id);
    }
}
