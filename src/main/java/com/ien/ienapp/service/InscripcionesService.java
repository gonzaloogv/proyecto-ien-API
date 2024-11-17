package com.ien.ienapp.service;

import com.ien.ienapp.dto.InscripcionesDTO;
import com.ien.ienapp.entity.Inscripciones;
import com.ien.ienapp.entity.InscripcionesId;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.Carrera;
import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.InscripcionesRepository;
import com.ien.ienapp.repository.AlumnoRepository;
import com.ien.ienapp.repository.CarreraRepository;
import com.ien.ienapp.repository.PlanesEstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionesService {

    @Autowired
    private InscripcionesRepository inscripcionesRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private PlanesEstudiosRepository planesEstudiosRepository;

    public InscripcionesDTO crearInscripcion(InscripcionesDTO inscripcionDTO) {
        Inscripciones inscripcion = new Inscripciones();

        // Crear la clave primaria compuesta
        InscripcionesId id = new InscripcionesId();
        id.setIdAlumno(inscripcionDTO.getIdAlumno());
        id.setIdCarrera(inscripcionDTO.getIdCarrera());

        // Asignar la clave primaria a la inscripción
        inscripcion.setId(id);

        // Continuar con el resto de la asignación de valores
        Alumno alumno = alumnoRepository.findById(inscripcionDTO.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
        inscripcion.setAlumno(alumno);

        Carrera carrera = carreraRepository.findById(inscripcionDTO.getIdCarrera())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
        inscripcion.setCarrera(carrera);

        if (inscripcionDTO.getIdPlanEstudio() != null) {
            PlanesEstudios planEstudio = planesEstudiosRepository.findById(inscripcionDTO.getIdPlanEstudio())
                    .orElseThrow(() -> new ResourceNotFoundException("Plan de estudios no encontrado"));
            inscripcion.setPlanesEstudios(planEstudio);
        }

        inscripcion.setNuDni(inscripcionDTO.getNuDni());
        inscripcion.setDeNombre(inscripcionDTO.getDeNombre());
        inscripcion.setDeApellido(inscripcionDTO.getDeApellido());
        inscripcion.setNuCelular(inscripcionDTO.getNuCelular());
        inscripcion.setNuTelefono(inscripcionDTO.getNuTelefono());
        inscripcion.setDeDireccion(inscripcionDTO.getDeDireccion());
        inscripcion.setDeGenero(inscripcionDTO.getDeGenero());
        inscripcion.setDeMail(inscripcionDTO.getDeMail());
        inscripcion.setFeNacimiento(inscripcionDTO.getFeNacimiento());
        inscripcion.setFeInscripcion(inscripcionDTO.getFeInscripcion());
        inscripcion.setFeRegistro(inscripcionDTO.getFeRegistro());
        inscripcion.setFeModificacion(inscripcionDTO.getFeModificacion());

        inscripcion = inscripcionesRepository.save(inscripcion);
        
        return convertirAInscripcionesDTO(inscripcion);
    }

    public List<InscripcionesDTO> obtenerInscripciones() {
        // Obtener todas las inscripciones y convertirlas a DTOs
        return inscripcionesRepository.findAll().stream()
                .map(this::convertirAInscripcionesDTO)
                .collect(Collectors.toList());
    }

    public List<InscripcionesDTO> obtenerInscripcionesPorAlumnoId(Integer idAlumno) { 
        return inscripcionesRepository.findById_IdAlumno(idAlumno).stream() 
                .map(this::convertirAInscripcionesDTO) 
                .collect(Collectors.toList()); 
    }

    private InscripcionesDTO convertirAInscripcionesDTO(Inscripciones inscripcion) {
        // Convertir entidad Inscripciones a DTO
        InscripcionesDTO dto = new InscripcionesDTO();
        dto.setIdAlumno(inscripcion.getAlumno().getId());
        dto.setIdCarrera(inscripcion.getCarrera().getId());
        dto.setIdPlanEstudio(inscripcion.getPlanesEstudios().getId());
        dto.setDeNombre(inscripcion.getDeNombre());
        dto.setDeApellido(inscripcion.getDeApellido());
        dto.setNuDni(inscripcion.getNuDni());
        dto.setNuCelular(inscripcion.getNuCelular());
        dto.setNuTelefono(inscripcion.getNuTelefono());
        dto.setDeDireccion(inscripcion.getDeDireccion());
        dto.setDeGenero(inscripcion.getDeGenero());
        dto.setDeMail(inscripcion.getDeMail());
        dto.setFeNacimiento(inscripcion.getFeNacimiento());
        dto.setFeInscripcion(inscripcion.getFeInscripcion());
        dto.setFeRegistro(inscripcion.getFeRegistro());
        dto.setFeModificacion(inscripcion.getFeModificacion());
        return dto;
    }
}
