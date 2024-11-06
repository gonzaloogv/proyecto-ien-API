package com.ien.ienapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.ProfesoresDTO;
import com.ien.ienapp.entity.Titulos;
import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.entity.ProfesoresTitulos;
import com.ien.ienapp.entity.ProfesoresTitulosId;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ProfesoresTitulosRepository;

@Service
public class ProfesoresTitulosService {

    @Autowired
    private ProfesoresTitulosRepository profesoresTitulosRepository;

    @Autowired 
    private TitulosService titulosService;

    @Autowired
    private RRHHService rrhhService; // Servicio para obtener el profesor

    public ProfesoresTitulos crearProfesoresTitulos(ProfesoresDTO profesoresDTO) {
        ProfesoresTitulos profesoresTitulos = new ProfesoresTitulos();
        
        // Crear el ID de ProfesoresTitulos
        ProfesoresTitulosId id = new ProfesoresTitulosId();
        id.setIdProfesor(profesoresDTO.getIdProfesor());
        id.setIdTitulo(profesoresDTO.getIdTitulo()); // Asegúrate de que este valor no sea null

        // Setear el ID en el objeto de ProfesoresTitulos
        profesoresTitulos.setId(id);

        // Obtener el objeto Profesor
        Profesor profesor = rrhhService.getProfesorById(profesoresDTO.getIdProfesor())
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado"));
        
        // Setear el objeto Profesor en ProfesoresTitulos
        profesoresTitulos.setProfesor(profesor);
        profesoresTitulos.setReImagenTitulo(profesoresDTO.getReImagenTitulo());
        profesoresTitulos.setFeRegistro(profesoresDTO.getFeRegistro());

        // Obtener el objeto Titulo
        if (profesoresDTO.getIdTitulo() != null) {
            Titulos titulos = titulosService.obtenerTitulosPorId(profesoresDTO.getIdTitulo())
                    .orElseThrow(() -> new ResourceNotFoundException("Titulo no encontrada"));
            profesoresTitulos.setTitulos(titulos);
        }

        return profesoresTitulosRepository.save(profesoresTitulos);
    }


    public ProfesoresTitulos actualizarProfesoresTitulos(Integer idProfesor, Integer idTitulo, ProfesoresDTO profesoresDTO) {
        // Crear una instancia de ProfesoresTitulosId usando los IDs de profesor y Titulo
        ProfesoresTitulosId id = new ProfesoresTitulosId(idProfesor, idTitulo);
    
        if (profesoresTitulosRepository.existsById(id)) {
            ProfesoresTitulos profesoresTitulos = profesoresTitulosRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Profesor-Titulo no encontrado"));
    
            // Buscar el profesor por ID y setearlo
            profesoresTitulos.setProfesor(rrhhService.getProfesorById(profesoresDTO.getIdProfesor())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado")));
            
            profesoresTitulos.setReImagenTitulo(profesoresDTO.getReImagenTitulo());
            profesoresTitulos.setFeModificacion(profesoresDTO.getFeModificacion());
    
            if (profesoresDTO.getIdTitulo() != null) {
                Titulos titulos = titulosService.obtenerTitulosPorId(profesoresDTO.getIdTitulo())
                        .orElseThrow(() -> new ResourceNotFoundException("Titulo no encontrada"));
                profesoresTitulos.setTitulos(titulos);
            }
    
            return profesoresTitulosRepository.save(profesoresTitulos);
        }
        throw new ResourceNotFoundException("Profesor-Titulo no encontrado");
    }
    

    public List<ProfesoresDTO> obtenerProfesoresTitulos() {
        return profesoresTitulosRepository.findAll().stream()
                .map(this::convertirProfesoresTitulosDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProfesoresTitulos> obtenerProfesoresTitulosPorId(Integer idProfesor, Integer idTitulo) {
        ProfesoresTitulosId id = new ProfesoresTitulosId(idProfesor, idTitulo);
        return profesoresTitulosRepository.findById(id);
    }
    

    public void eliminarProfesoresTitulos(Integer idProfesor, Integer idTitulo) {
        ProfesoresTitulosId id = new ProfesoresTitulosId(idProfesor, idTitulo);
        if (profesoresTitulosRepository.existsById(id)) {
            profesoresTitulosRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Profesor-Titulo no encontrado");
        }
    }
    

    public ProfesoresDTO convertirProfesoresTitulosDTO(ProfesoresTitulos profesoresTitulos) {
        ProfesoresDTO dto = new ProfesoresDTO();
        dto.setIdProfesor(profesoresTitulos.getProfesor().getId());
        dto.setIdTitulo(profesoresTitulos.getTitulos().getId()); // Se asume que Profesor tiene un método getId
        dto.setReImagenTitulo(profesoresTitulos.getReImagenTitulo());
        dto.setFeRegistro(profesoresTitulos.getFeRegistro());
        dto.setFeModificacion(profesoresTitulos.getFeModificacion());
        return dto;
    }
}
