package com.ien.ienapp.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Horario;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public Horario crearHorario(ComisionDetalleDTO comisionDetalleDTO) {
        Horario horario = new Horario();
        horario.setIdHorario(comisionDetalleDTO.getIdHorario());
        horario.setHrInicio(comisionDetalleDTO.getHrInicio());
        horario.setHrFin(comisionDetalleDTO.getHrFin());
        horario.setNuDia(comisionDetalleDTO.getNuDia());
        horario.setFeRegistro(comisionDetalleDTO.getFeRegistro());
        return horarioRepository.save(horario); 
    }

    public List<ComisionDetalleDTO> obtenerHorarios() {
        return horarioRepository.findAll().stream()
                .map(this::convertirHorarioDTO)
                .collect(Collectors.toList());
    }

    public Horario obtenerHorarioId(Integer id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario por id no encontrado"));
    } 


    public Horario actHorario(Integer id, ComisionDetalleDTO comisionDetalleDTO) {
        if (horarioRepository.existsById(id)) {
            Horario horario = new Horario();
            horario.setIdHorario(id);
            horario.setHrInicio(comisionDetalleDTO.getHrInicio());
            horario.setHrFin(comisionDetalleDTO.getHrFin());
            horario.setNuDia(comisionDetalleDTO.getNuDia());
            horario.setFeModificacion(comisionDetalleDTO.getFeModificacion());

            return horarioRepository.save(horario);
        }
        throw new ResourceNotFoundException("Horario no existe");
    }

    public ComisionDetalleDTO convertirHorarioDTO(Horario horario) {
        ComisionDetalleDTO dto = new ComisionDetalleDTO();
        dto.setIdHorario(horario.getIdHorario());
        dto.setHrInicio(horario.getHrInicio());
        dto.setHrFin(horario.getHrFin());
        dto.setNuDia(horario.getNuDia());
        dto.setFeRegistro(horario.getFeRegistro());
        return dto;
    }

    public void eliminarHorario(Integer id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Horario inexistente");
        }
    }
}
