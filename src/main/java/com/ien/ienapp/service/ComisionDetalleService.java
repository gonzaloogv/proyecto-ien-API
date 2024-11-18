package com.ien.ienapp.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.entity.Aula;
import com.ien.ienapp.entity.Comision;
import com.ien.ienapp.entity.ComisionDetalle;
import com.ien.ienapp.entity.Horario;
import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ComisionDetalleRepository;

@Service
public class ComisionDetalleService {

    @Autowired
    private ComisionDetalleRepository comisionDetalleRepository;

    @Autowired
    private ComisionService comisionService;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private HorarioService horarioService;

    public ComisionDetalleService(ComisionDetalleRepository comisionDetalleRepository) {
        this.comisionDetalleRepository = comisionDetalleRepository;
    }

    public ComisionDetalle crearComisionDetalle(ComisionDetalleDTO comisionDetalleDTO) {
        ComisionDetalle comisionDetalle = new ComisionDetalle();
        comisionDetalle.setId(comisionDetalleDTO.getId());
        comisionDetalle.setFeRegistro(comisionDetalleDTO.getFeRegistro());
    
        if (comisionDetalleDTO.getIdComision() != null) {
            Comision comision = comisionService.obtenerComisionPorId(comisionDetalleDTO.getIdComision());
            if (comision == null) {
                throw new ResourceNotFoundException("Comisión no encontrada");
            }
            comisionDetalle.setComision(comision);
        }

        if (comisionDetalleDTO.getIdAlumnos() != null) {
            Alumno alumno = alumnoService.obtenerAlumnoPorId(comisionDetalleDTO.getIdAlumnos());
            comisionDetalle.setAlumno(alumno);
        }
    
        // Verificar y asignar la Materia
        if (comisionDetalleDTO.getIdMateria() != null) {
            Materia materia = materiaService.obtenerMateriaPorId(comisionDetalleDTO.getIdMateria())
                .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada")); // Asegúrate que este método devuelva un Optional
            comisionDetalle.setMateria(materia);
        }
    
        // Verificar y asignar el Aula
        if (comisionDetalleDTO.getIdAula() != null) {
            Aula aula = aulaService.obtenerAulasPorId(comisionDetalleDTO.getIdAula());
            comisionDetalle.setAula(aula);
        }
    
        // Verificar y asignar el Horario
        if (comisionDetalleDTO.getIdHorario() != null) {
            Horario horario = horarioService.obtenerHorarioId(comisionDetalleDTO.getIdHorario());
            comisionDetalle.setHorario(horario);
        }
    
        return comisionDetalleRepository.save(comisionDetalle); 
    }
    
    public List<ComisionDetalleDTO> obtenerComisionDetalle() {
        return comisionDetalleRepository.findAll().stream()
                .map(this::convertirComisionDetalleDTO)
                .collect(Collectors.toList());
    }

    public ComisionDetalle obtenerComisionDetalleId(Integer id) {
        return comisionDetalleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ComisionD por id no encontrado"));
    } 

    public ComisionDetalleDTO convertirComisionDetalleDTO(ComisionDetalle comisionDetalle) {
        ComisionDetalleDTO dto = new ComisionDetalleDTO();
        
        dto.setId(comisionDetalle.getId());
        
        if (comisionDetalle.getComision() != null) {
            dto.setIdComision(comisionDetalle.getComision().getIdComision());
        }
        
        if (comisionDetalle.getAlumno() != null) {
            dto.setIdAlumnos(comisionDetalle.getAlumno().getId());
        }
        
        if (comisionDetalle.getMateria() != null) {
            dto.setIdMateria(comisionDetalle.getMateria().getId());
        }
        
        if (comisionDetalle.getAula() != null) {
            dto.setIdAula(comisionDetalle.getAula().getIdAula());
        }
        
        if (comisionDetalle.getHorario() != null) {
            dto.setIdHorario(comisionDetalle.getHorario().getIdHorario());
        }
    
        return dto;
    }
    public List<ComisionDetalle> getAlumnosByMateriaId(Integer idMateria) { 
        return comisionDetalleRepository.findByMateriaId(idMateria); 
    }

    public void eliminarComisionDetalle(Integer id) {
        if (comisionDetalleRepository.existsById(id)) {
            comisionDetalleRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("ComisionD inexistente");
        }
    }
}
