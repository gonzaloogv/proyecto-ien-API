package com.ien.ienapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.ProfesoresDTO;
import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.entity.ProfesoresMaterias;
import com.ien.ienapp.entity.ProfesoresMateriasId;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ProfesoresMateriasRepository;

@Service
public class ProfesoresMateriasService {

    @Autowired
    private ProfesoresMateriasRepository profesoresMateriasRepository;

    @Autowired 
    private MateriaService materiaService;

    @Autowired
    private RRHHService rrhhService; // Servicio para obtener el profesor

    public ProfesoresMaterias crearProfesoresMaterias(ProfesoresDTO profesoresDTO) {
        ProfesoresMaterias profesoresMaterias = new ProfesoresMaterias();

        ProfesoresMateriasId id = new ProfesoresMateriasId();
        id.setIdProfesor(profesoresDTO.getIdProfesor());
        id.setIdMateria(profesoresDTO.getIdMateria()); // Asegúrate de que este valor no sea null

        profesoresMaterias.setId(id);


        Profesor profesor = rrhhService.getProfesorById(profesoresDTO.getIdProfesor())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));

        profesoresMaterias.setProfesor(profesor);
        profesoresMaterias.setTiCargo(profesoresDTO.getTiCargo());
        profesoresMaterias.setFeRegistro(profesoresDTO.getFeRegistro());

        if (profesoresDTO.getIdMateria() != null) {
            Materia materia = materiaService.obtenerMateriaPorId(profesoresDTO.getIdMateria())
                    .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada"));
            profesoresMaterias.setMateria(materia);
        }

        return profesoresMateriasRepository.save(profesoresMaterias);
    }

    public List<ProfesoresDTO> obtenerMateriasPorIdProfesor(Integer idProfesor) {
        List<ProfesoresMaterias> profesoresMateriasList = profesoresMateriasRepository.findById_IdProfesor(idProfesor);
        return profesoresMateriasList.stream()
                .map(this::convertirProfesoresMateriasDTO)
                .collect(Collectors.toList());
    }

    public ProfesoresMaterias actualizarProfesoresMaterias(Integer idProfesor, Integer idMateria, ProfesoresDTO profesoresDTO) {
        // Crear una instancia de ProfesoresMateriasId usando los IDs de profesor y materia
        ProfesoresMateriasId id = new ProfesoresMateriasId(idProfesor, idMateria);
    
        if (profesoresMateriasRepository.existsById(id)) {
            ProfesoresMaterias profesoresMaterias = profesoresMateriasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Profesor-Materia no encontrado"));
    
            // Buscar el profesor por ID y setearlo
            profesoresMaterias.setProfesor(rrhhService.getProfesorById(profesoresDTO.getIdProfesor())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado")));
            
            profesoresMaterias.setTiCargo(profesoresDTO.getTiCargo());
            profesoresMaterias.setFeModificacion(profesoresDTO.getFeModificacion());
    
            if (profesoresDTO.getIdMateria() != null) {
                Materia materia = materiaService.obtenerMateriaPorId(profesoresDTO.getIdMateria())
                        .orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada"));
                profesoresMaterias.setMateria(materia);
            }
    
            return profesoresMateriasRepository.save(profesoresMaterias);
        }
        throw new ResourceNotFoundException("Profesor-Materia no encontrado");
    }
    

    public List<ProfesoresDTO> obtenerProfesoresMaterias() {
        return profesoresMateriasRepository.findAll().stream()
                .map(this::convertirProfesoresMateriasDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProfesoresMaterias> obtenerProfesoresMateriasPorId(Integer idProfesor, Integer idMateria) {
        ProfesoresMateriasId id = new ProfesoresMateriasId(idProfesor, idMateria);
        return profesoresMateriasRepository.findById(id);
    }
    

    public void eliminarProfesoresMaterias(Integer idProfesor, Integer idMateria) {
        ProfesoresMateriasId id = new ProfesoresMateriasId(idProfesor, idMateria);
        if (profesoresMateriasRepository.existsById(id)) {
            profesoresMateriasRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Profesor-Materia no encontrado");
        }
    }
    

    public ProfesoresDTO convertirProfesoresMateriasDTO(ProfesoresMaterias profesoresMaterias) {
        ProfesoresDTO dto = new ProfesoresDTO();
        dto.setIdProfesor(profesoresMaterias.getProfesor().getId());
        dto.setIdMateria(profesoresMaterias.getMateria().getId()); // Se asume que Profesor tiene un método getId
        dto.setTiCargo(profesoresMaterias.getTiCargo());
        dto.setFeRegistro(profesoresMaterias.getFeRegistro());
        return dto;
    }
}
