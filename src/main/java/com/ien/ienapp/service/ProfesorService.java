package com.ien.ienapp.service;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor crearProfesor(RRHHDTO rrhhDTO) {
        Profesor profesor = new Profesor();
        profesor.setId(rrhhDTO.getId());
        profesor.setNuMatricula(rrhhDTO.getNuMatricula());
        profesor.setFeBaja(rrhhDTO.getFeBaja());
        profesor.setFeIngreso(rrhhDTO.getFeIngreso());
        profesor.setFeRegistro(rrhhDTO.getFeRegistro());
        profesor.setFeModificacion(rrhhDTO.getFeModificacion());

        return profesorRepository.save(profesor);
    }
    
    public List<RRHHDTO> obtenerTodosLosProfesores() {
        return profesorRepository.findAll().stream()
                .map(this::convertirAProfesorDTO)
                .collect(Collectors.toList());
    }

    public RRHHDTO obtenerProfesorPorId(Integer id) {
        return profesorRepository.findById(id)
                .map(this::convertirAProfesorDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
    }    
        
    public Profesor actualizarProfesor(Integer id, RRHHDTO rrhhDTO) {
        if (profesorRepository.existsById(id)) {
            Profesor profesor = new Profesor();
            profesor.setId(id);
            profesor.setNuMatricula(rrhhDTO.getNuMatricula());
            profesor.setFeIngreso(rrhhDTO.getFeIngreso());
            profesor.setFeBaja(rrhhDTO.getFeBaja());
            profesor.setFeModificacion(rrhhDTO.getFeModificacion());
            profesor.setFeRegistro(rrhhDTO.getFeRegistro());

            return profesorRepository.save(profesor);
        }
        throw new ResourceNotFoundException("Profesor no encontrado");
    }   

    public void eliminarProfesor(Integer id) {
        if (profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Profesor no encontrado");
        }
    }

    private RRHHDTO convertirAProfesorDTO(Profesor profesor) {
        RRHHDTO dto = new RRHHDTO();
        dto.setId(profesor.getId());
        dto.setNuMatricula(profesor.getNuMatricula());
        dto.setFeBaja(profesor.getFeBaja());
        dto.setFeIngreso(profesor.getFeIngreso());
        dto.setFeRegistro(profesor.getFeRegistro());
        return dto;
    }
}
