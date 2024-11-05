package com.ien.ienapp.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Comision;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ComisionRepository;

@Service
public class ComisionService {

    @Autowired
    private ComisionRepository comisionRepository;

    public ComisionService(ComisionRepository comisionRepository) {
        this.comisionRepository = comisionRepository;
    }

    public Comision crearComision(ComisionDetalleDTO comisionDetalleDTO) {
        Comision comision = new Comision();
        comision.setIdComision(comisionDetalleDTO.getIdComision());
        comision.setDeDescripcion(comisionDetalleDTO.getDeDescripcion());
        comision.setNuCodigoComision(comisionDetalleDTO.getNuCodigoComision());
        comision.setNuAnioComision(comisionDetalleDTO.getNuAnioComision());
        comision.setDeCarrera(comisionDetalleDTO.getDeCarrera());
        comision.setNuAnioDeMateria(comisionDetalleDTO.getNuAnioDeMateria());
        comision.setFeRegistro(comisionDetalleDTO.getFeRegistro());
        return comisionRepository.save(comision); 
    }

    public List<ComisionDetalleDTO> obtenerComision() {
        return comisionRepository.findAll().stream()
                .map(this::convertirComisionDTO)
                .collect(Collectors.toList());
    }

    public Comision obtenerComisionPorId(Integer id) {
        return comisionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comisión no encontrada por id"));
    }
    

    public Comision actComision(Integer id, ComisionDetalleDTO comisionDetalleDTO) {
        if (comisionRepository.existsById(id)) {
            Comision comision = new Comision();
            comision.setIdComision(id);
            comision.setDeDescripcion(comisionDetalleDTO.getDeDescripcion());
            comision.setNuCodigoComision(comisionDetalleDTO.getNuCodigoComision());
            comision.setNuAnioComision(comisionDetalleDTO.getNuAnioComision());
            comision.setDeCarrera(comisionDetalleDTO.getDeCarrera());
            comision.setNuAnioDeMateria(comisionDetalleDTO.getNuAnioDeMateria());
            comision.setFeModificacion(comisionDetalleDTO.getFeModificacion());

            return comisionRepository.save(comision);
        }
        throw new ResourceNotFoundException("Comision no existe");
    }

    public ComisionDetalleDTO convertirComisionDTO(Comision comision) {
        ComisionDetalleDTO dto = new ComisionDetalleDTO();
        dto.setIdComision(comision.getIdComision());
        dto.setDeDescripcion(comision.getDeDescripcion());
        dto.setNuCodigoComision(comision.getNuCodigoComision());
        dto.setDeCarrera(comision.getDeCarrera());
        dto.setNuAnioComision(comision.getNuAnioComision());
        dto.setFeRegistro(comision.getFeRegistro());
        return dto;
    }

    public void eliminarComision(Integer id) {
        if (comisionRepository.existsById(id)) {
            comisionRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Comision inexistente");
        }
    }
}
