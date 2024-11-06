package com.ien.ienapp.service;

import com.ien.ienapp.dto.OperacionDTO;
import com.ien.ienapp.entity.Modulo;
import com.ien.ienapp.entity.Operacion;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.ModuloRepository;
import com.ien.ienapp.repository.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperacionService {

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    public OperacionDTO convertirAOperacionDTO(Operacion operacion) {
        OperacionDTO operacionDTO = new OperacionDTO();
        operacionDTO.setId(operacion.getId());
        operacionDTO.setDeOperacion(operacion.getDeOperacion());
        operacionDTO.setIdModulo(operacion.getModulo().getId());
        operacionDTO.setFeRegistro(operacion.getFeRegistro());
        operacionDTO.setFeModificacion(operacion.getFeModificacion());
        return operacionDTO;
    }

    public List<OperacionDTO> obtenerTodasLasOperaciones() {
        List<Operacion> operaciones = operacionRepository.findAll();
        return operaciones.stream()
                          .map(this::convertirAOperacionDTO)
                          .collect(Collectors.toList());
    }

    public Optional<OperacionDTO> obtenerOperacionPorId(Integer id) {
        Optional<Operacion> operacionOptional = operacionRepository.findById(id);
        return operacionOptional.map(this::convertirAOperacionDTO);
    }

    public OperacionDTO crearOperacion(OperacionDTO operacionDTO) {
        Operacion operacion = new Operacion();
        operacion.setId(operacionDTO.getId());
        operacion.setDeOperacion(operacionDTO.getDeOperacion());
        operacion.setFeRegistro(operacionDTO.getFeRegistro()); 
        operacion.setFeModificacion(operacionDTO.getFeModificacion());
        
        Modulo modulo = moduloRepository.findById(operacionDTO.getIdModulo())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrada"));
        operacion.setModulo(modulo);

        operacion = operacionRepository.save(operacion);
        return convertirAOperacionDTO(operacion);
    }

    public Optional<OperacionDTO> actualizarOperacion(Integer id, OperacionDTO operacionDTO) {
        Optional<Operacion> operacionOptional = operacionRepository.findById(id);
        if (operacionOptional.isPresent()) {
            Operacion operacion = operacionOptional.get();
            operacion.setDeOperacion(operacionDTO.getDeOperacion());
            operacion.setFeModificacion(operacionDTO.getFeModificacion());

            Modulo modulo = moduloRepository.findById(operacionDTO.getIdModulo())
                    .orElseThrow(() -> new ResourceNotFoundException("Módulo no encontrado"));
            operacion.setModulo(modulo);

            operacion = operacionRepository.save(operacion);

            return Optional.of(convertirAOperacionDTO(operacion));
        }
        return Optional.empty();
    }

    public boolean eliminarOperacion(Integer id) {
        Optional<Operacion> operacionOptional = operacionRepository.findById(id);
        if (operacionOptional.isPresent()) {
            operacionRepository.delete(operacionOptional.get());
            return true;
        }
        return false;
    }
}
