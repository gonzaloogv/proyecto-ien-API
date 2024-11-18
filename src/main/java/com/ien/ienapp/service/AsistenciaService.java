package com.ien.ienapp.service;

import com.ien.ienapp.dto.AsistenciaDTO;
import com.ien.ienapp.entity.Asistencias;
import com.ien.ienapp.entity.ComisionDetalle;
import com.ien.ienapp.repository.AsistenciasRepository;
import com.ien.ienapp.repository.ComisionDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciasRepository asistenciasRepository;

    @Autowired
    private ComisionDetalleRepository comisionDetalleRepository;

    public AsistenciaDTO crearAsistencia(AsistenciaDTO asistenciaDTO) {
        Optional<ComisionDetalle> comisionDetalleOptional = comisionDetalleRepository.findById(asistenciaDTO.getIdComisionDetalle());
        if (!comisionDetalleOptional.isPresent()) {
            throw new IllegalArgumentException("ComisionDetalle con ID " + asistenciaDTO.getIdComisionDetalle() + " no encontrado.");
        }

        Asistencias asistencia = new Asistencias();
        asistencia.setComisionDetalle(comisionDetalleOptional.get());
        asistencia.setFeAsistencia(asistenciaDTO.getFeAsistencia());
        asistencia.setNuAsistencia(asistenciaDTO.getNuAsistencia());

        Asistencias asistenciaGuardada = asistenciasRepository.save(asistencia);

        AsistenciaDTO asistenciaGuardadaDTO = new AsistenciaDTO();
        asistenciaGuardadaDTO.setId(asistenciaGuardada.getId());
        asistenciaGuardadaDTO.setIdComisionDetalle(asistenciaGuardada.getComisionDetalle().getId());
        asistenciaGuardadaDTO.setFeAsistencia(asistenciaGuardada.getFeAsistencia());
        asistenciaGuardadaDTO.setNuAsistencia(asistenciaGuardada.getNuAsistencia());

        return asistenciaGuardadaDTO;
    }

    public List<AsistenciaDTO> getAllAsistencias() {
        return asistenciasRepository.findAll().stream()
                .map(asistencia -> {
                    AsistenciaDTO dto = new AsistenciaDTO();
                    dto.setId(asistencia.getId());
                    dto.setIdComisionDetalle(asistencia.getComisionDetalle().getId());
                    dto.setFeAsistencia(asistencia.getFeAsistencia());
                    dto.setNuAsistencia(asistencia.getNuAsistencia());
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<AsistenciaDTO> getAsistenciasByComisionDetalleId(Integer idComisionDetalle) {
        return asistenciasRepository.findByComisionDetalle_Id(idComisionDetalle).stream()
                .map(asistencia -> {
                    AsistenciaDTO dto = new AsistenciaDTO();
                    dto.setId(asistencia.getId());
                    dto.setIdComisionDetalle(asistencia.getComisionDetalle().getId());
                    dto.setFeAsistencia(asistencia.getFeAsistencia());
                    dto.setNuAsistencia(asistencia.getNuAsistencia());
                    return dto;
                }).collect(Collectors.toList());
    }

    // Otros métodos, como actualizar, eliminar...
}
