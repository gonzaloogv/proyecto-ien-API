package com.ien.ienapp.service;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Aula;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public Aula crearAula(ComisionDetalleDTO comisionDetalleDTO) {
        Aula aula = new Aula();
        aula.setIdAula(comisionDetalleDTO.getIdAula());
        aula.setNuCapacidadMax(comisionDetalleDTO.getNuCapacidadMax());
        aula.setFeRegistro(comisionDetalleDTO.getFeRegistro());
        aula.setFeModificacion(comisionDetalleDTO.getFeModificacion());
        return aulaRepository.save(aula);
    }
    
    public List<ComisionDetalleDTO> obtenerAulas() {
        return aulaRepository.findAll().stream()
                .map(this::convertirAulaDTO)
                .collect(Collectors.toList());
    }

    public Aula obtenerAulasPorId(Integer id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula inexistente"));
    }   
    
        
    public Aula actualizarAula(Integer id, ComisionDetalleDTO comisionDetalleDTO) {
        if (aulaRepository.existsById(id)) {
            Aula aula = new Aula();
            aula.setIdAula(id);
            aula.setNuCapacidadMax(comisionDetalleDTO.getNuCapacidadMax());
            aula.setFeModificacion(comisionDetalleDTO.getFeModificacion());
            return aulaRepository.save(aula);
        }
        throw new ResourceNotFoundException("Aula no encontrada");
    }   

    public void eliminarAula(Integer id) {
        if (aulaRepository.existsById(id)) {
            aulaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Aula no encontrada");
        }
    }

    public ComisionDetalleDTO convertirAulaDTO(Aula aula) {
        ComisionDetalleDTO dto = new ComisionDetalleDTO();
        dto.setIdAula(aula.getIdAula());
        dto.setNuCapacidadMax(aula.getNuCapacidadMax());
        dto.setFeRegistro(aula.getFeRegistro());
        return dto;
    }
}
