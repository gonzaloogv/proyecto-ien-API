package com.ien.ienapp.service;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private PlanesEstudiosService planesEstudioService;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public Alumno crearAlumno(RRHHDTO rrhhDTO) {
        Alumno alumno = new Alumno();
        alumno.setId(rrhhDTO.getId());
        alumno.setNuLegajo(rrhhDTO.getNuLegajo());
        alumno.setFeIngreso(rrhhDTO.getFeIngreso());
        alumno.setFeEgreso(rrhhDTO.getFeEgreso());
        alumno.setNuPromedioGral(rrhhDTO.getNuPromedioGral());
        alumno.setTiEstadoInscripcion(rrhhDTO.getTiEstadoInscripcion());
        alumno.setFeRegistro(rrhhDTO.getFeRegistro());

        if (rrhhDTO.getIdPlanEstudio() != null) {
            PlanesEstudios planesEstudios = planesEstudioService.getPlanesEstudiosById(rrhhDTO.getIdPlanEstudio())
                    .orElseThrow(() -> new ResourceNotFoundException("Plan de estudios no encontrado"));
            alumno.setPlanesEstudios(planesEstudios);
        }

        return alumnoRepository.save(alumno);
    }

    public List<RRHHDTO> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll().stream()
                .map(this::convertirAAlumnoDTO)
                .collect(Collectors.toList());
    }

    public Alumno obtenerAlumnoPorId(Integer id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
    }    

    public Alumno actualizarAlumno(Integer id, RRHHDTO rrhhDTO) {
        if (alumnoRepository.existsById(id)) {
            Alumno alumno = new Alumno();
            alumno.setId(id);
            alumno.setNuLegajo(rrhhDTO.getNuLegajo());
            alumno.setFeIngreso(rrhhDTO.getFeIngreso());
            alumno.setFeEgreso(rrhhDTO.getFeEgreso());
            alumno.setNuPromedioGral(rrhhDTO.getNuPromedioGral());
            alumno.setTiEstadoInscripcion(rrhhDTO.getTiEstadoInscripcion());
            alumno.setFeModificacion(rrhhDTO.getFeModificacion());

            if (rrhhDTO.getIdPlanEstudio() != null) {
                PlanesEstudios planesEstudios = planesEstudioService.getPlanesEstudiosById(rrhhDTO.getIdPlanEstudio())
                        .orElseThrow(() -> new ResourceNotFoundException("Plan de estudios no encontrado"));
                alumno.setPlanesEstudios(planesEstudios);
            }
            return alumnoRepository.save(alumno);
        }
        throw new ResourceNotFoundException("Alumno no encontrado");
    }

    public void eliminarAlumno(Integer id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Alumno no encontrado");
        }
    }

    public RRHHDTO convertirAAlumnoDTO(Alumno alumno) {
        RRHHDTO dto = new RRHHDTO();
        dto.setId(alumno.getId());
        dto.setNuLegajo(alumno.getNuLegajo());
        dto.setFeIngreso(alumno.getFeIngreso());
        dto.setFeEgreso(alumno.getFeEgreso());
        dto.setNuPromedioGral(alumno.getNuPromedioGral());
        dto.setTiEstadoInscripcion(alumno.getTiEstadoInscripcion());
        dto.setFeRegistro(alumno.getFeRegistro());
        if (alumno.getPlanesEstudios() != null) {
            dto.setIdPlanEstudio(alumno.getPlanesEstudios().getId());
        }
        return dto;
    }
}
