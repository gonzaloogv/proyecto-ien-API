package com.ien.ienapp.service;

import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> getProfesorById(Integer id) {
        return profesorRepository.findById(id);
    }

    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor updateProfesor(Integer id, Profesor updatedProfesor) {
        return profesorRepository.findById(id).map(profesor -> {
            profesor.setFeBaja(updatedProfesor.getFeBaja());
            profesor.setFeIngreso(updatedProfesor.getFeIngreso());
            profesor.setFeModificacion(updatedProfesor.getFeModificacion());
            profesor.setFeRegistro(updatedProfesor.getFeRegistro());
            profesor.setNuMatricula(updatedProfesor.getNuMatricula());
            profesor.setRrhh(updatedProfesor.getRrhh());
            return profesorRepository.save(profesor);
        }).orElseGet(() -> {
            updatedProfesor.setId(id);
            return profesorRepository.save(updatedProfesor);
        });
    }

    public void deleteProfesor(Integer id) {
        profesorRepository.deleteById(id);
    }
}
