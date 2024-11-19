package com.ien.ienapp.service;

import com.ien.ienapp.dto.ExamenDTO;
import com.ien.ienapp.entity.Examen;
import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ExamenRepository;
import com.ien.ienapp.repository.MateriaRepository;
import com.ien.ienapp.repository.ProfesorRepository;
import com.ien.ienapp.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private AlumnoRepository alumnoRepository; 

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public ExamenDTO crearExamen(ExamenDTO examenDTO) {
        Examen examen = new Examen();
        examen.setId(examenDTO.getId());
        Alumno alumno = alumnoRepository.findById(examenDTO.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrada"));
        Profesor profesor = profesorRepository.findById(examenDTO.getIdProfesor())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
        Materia materia = materiaRepository.findById(examenDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada"));
        examen.setAlumno(alumno);
        examen.setDeCondicion(examenDTO.getDeCondicion());
        examen.setFeExamen(examenDTO.getFeExamen());
        examen.setFeModificacion(examenDTO.getFeModificacion());
        examen.setFeRegistro(examenDTO.getFeRegistro());
        examen.setHrExamen(examenDTO.getHrExamen());
        examen.setMateria(materia);
        examen.setNuNota(examenDTO.getNuNota());
        examen.setProfesor(profesor);
        examen.setSnAusente(examenDTO.getSnAusente());
        examen.setTiExamen(examenDTO.getTiExamen());
    
        examen = examenRepository.save(examen);

        return convertirExamenDTO(examen);
    }

    public List<ExamenDTO> obtenerExamen() {
        return examenRepository.findAll().stream()
                .map(this::convertirExamenDTO)
                .collect(Collectors.toList());
    }

    public ExamenDTO obtenerExamenPorId(Integer id) {
        return examenRepository.findById(id)
                .map(this::convertirExamenDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Examen no encontrado"));
    }

    public ExamenDTO actualizarExamen(Integer id, ExamenDTO examenDTO) {
        Examen examen = examenRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Examen no encontrado"));

        examen.setDeCondicion(examenDTO.getDeCondicion());
        examen.setFeExamen(examenDTO.getFeExamen());
        examen.setFeModificacion(examenDTO.getFeModificacion());
        examen.setHrExamen(examenDTO.getHrExamen());
        examen.setNuNota(examenDTO.getNuNota());
        examen.setSnAusente(examenDTO.getSnAusente());
        examen.setTiExamen(examenDTO.getTiExamen());
        if (examenDTO.getIdAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(examenDTO.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
            examen.setAlumno(alumno);
        }
        if (examenDTO.getIdMateria() != null) {
            Materia materia = materiaRepository.findById(examenDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada"));
            examen.setMateria(materia);
        }
        if (examenDTO.getIdProfesor() != null) {
            Profesor profesor = profesorRepository.findById(examenDTO.getIdProfesor())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
            examen.setProfesor(profesor);
        }

        examen = examenRepository.save(examen);

        return convertirExamenDTO(examen);
    }

    public List<Examen> getExamenesByMateriaId(Integer idMateria) { 
        return examenRepository.findByMateria_Id(idMateria); 
    }

    public List<Examen> getCorreccionesByProfesorId(Integer idProfesor) { 
        return examenRepository.findByProfesor_Id(idProfesor); 
    }

    public void eliminarExamen(Integer id) {
        if (examenRepository.existsById(id)) {
            examenRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Examen no encontrado");
        }
    }

    private ExamenDTO convertirExamenDTO(Examen examen) {
        ExamenDTO dto = new ExamenDTO();
        dto.setId(examen.getId());
        dto.setIdAlumno(examen.getAlumno().getId());
        dto.setIdMateria(examen.getMateria().getId());
        dto.setIdProfesor(examen.getProfesor().getId());
        dto.setDeCondicion(examen.getDeCondicion());
        dto.setFeExamen(examen.getFeExamen());
        dto.setFeModificacion(examen.getFeModificacion());
        dto.setFeRegistro(examen.getFeRegistro());
        dto.setHrExamen(examen.getHrExamen());
        dto.setNuNota(examen.getNuNota());
        dto.setSnAusente(examen.getSnAusente());
        dto.setTiExamen(examen.getTiExamen());
        return dto;
    }
}
