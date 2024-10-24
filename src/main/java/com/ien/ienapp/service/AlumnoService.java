package com.ien.ienapp.service;

import com.ien.ienapp.dto.AlumnoDTO;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.entity.RRHH;
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
    private RRHHService rrhhService;

    @Autowired
    private PlanesEstudiosService planesEstudioService;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public Alumno crearAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno();
        alumno.setNuLegajo(alumnoDTO.getNuLegajo());
        alumno.setFeIngreso(alumnoDTO.getFeIngreso());
        alumno.setFeEgreso(alumnoDTO.getFeEgreso());
        alumno.setNuPromedioGral(alumnoDTO.getNuPromedioGral());
        alumno.setTiEstadoInscripcion(alumnoDTO.getTiEstadoInscripcion());
        alumno.setFeRegistro(alumnoDTO.getFeRegistro());

        // Establecer la relación con PlanEstudio
        if (alumnoDTO.getIdPlanEstudio() != null) {
            PlanesEstudios planesEstudios = planesEstudioService.getPlanesEstudiosById(alumnoDTO.getIdPlanEstudio())
                    .orElseThrow(() -> new ResourceNotFoundException("Plan de estudios no encontrado"));
            alumno.setPlanesEstudios(planesEstudios);
        }

        // Establecer la relación con RRHH
        if (alumnoDTO.getIdRrhh() != null) {
            RRHH rrhh = rrhhService.getRRHHById(alumnoDTO.getIdRrhh())
                    .orElseThrow(() -> new ResourceNotFoundException("RRHH no encontrado"));
            alumno.setRrhh(rrhh);
        } else {
            throw new ResourceNotFoundException("El campo idRrhh es obligatorio");
        }

        return alumnoRepository.save(alumno);
    }

    public List<AlumnoDTO> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll().stream()
                .map(this::convertirAAlumnoDTO) // Convertir Alumno a AlumnoDTO
                .collect(Collectors.toList());
    }

    public AlumnoDTO obtenerAlumnoPorId(Integer id) {
        return alumnoRepository.findById(id)
                .map(this::convertirAAlumnoDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
    }    

    public Alumno actualizarAlumno(Integer id, AlumnoDTO alumnoDTO) {
        if (alumnoRepository.existsById(id)) {
            Alumno alumno = new Alumno();
            alumno.setId(id); // Aseguramos que el ID es el correcto
            alumno.setNuLegajo(alumnoDTO.getNuLegajo());
            alumno.setFeIngreso(alumnoDTO.getFeIngreso());
            alumno.setFeEgreso(alumnoDTO.getFeEgreso());
            alumno.setNuPromedioGral(alumnoDTO.getNuPromedioGral());
            alumno.setTiEstadoInscripcion(alumnoDTO.getTiEstadoInscripcion());
            alumno.setFeRegistro(alumnoDTO.getFeRegistro());

            // Establecer la relación con PlanEstudio
            if (alumnoDTO.getIdPlanEstudio() != null) {
                PlanesEstudios planesEstudios = planesEstudioService.getPlanesEstudiosById(alumnoDTO.getIdPlanEstudio())
                        .orElseThrow(() -> new ResourceNotFoundException("Plan de estudios no encontrado"));
                alumno.setPlanesEstudios(planesEstudios);
            }

            // Establecer la relación con RRHH
            if (alumnoDTO.getIdRrhh() != null) {
                RRHH rrhh = rrhhService.getRRHHById(alumnoDTO.getIdRrhh())
                        .orElseThrow(() -> new ResourceNotFoundException("RRHH no encontrado"));
                alumno.setRrhh(rrhh);
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

    private AlumnoDTO convertirAAlumnoDTO(Alumno alumno) {
        AlumnoDTO dto = new AlumnoDTO();
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
        if (alumno.getRrhh() != null) {
            dto.setIdRrhh(alumno.getRrhh().getId()); // Asumiendo que RRHH tiene un método getId
        }
        return dto;
    }
}
