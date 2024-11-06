package com.ien.ienapp.service;

import com.ien.ienapp.dto.CuotasDTO;
import com.ien.ienapp.entity.Cuotas;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.CuotasRepository;
import com.ien.ienapp.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuotasService {

    @Autowired
    private CuotasRepository cuotasRepository;

    @Autowired
    private AlumnoRepository alumnoRepository; 

    public CuotasDTO crearCuota(CuotasDTO cuotasDTO) {
        Cuotas cuota = new Cuotas();
        cuota.setPrCuota(cuotasDTO.getPrCuota());

        Alumno alumno = alumnoRepository.findById(cuotasDTO.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrada"));
        
        cuota.setAlumno(alumno);
        cuota.setFeRegistro(cuotasDTO.getFeRegistro());
        cuota.setFeModificacion(cuotasDTO.getFeModificacion());
    
        cuota = cuotasRepository.save(cuota);

        return convertirCuotasDTO(cuota);
    }

    public List<CuotasDTO> obtenerCuotas() {
        return cuotasRepository.findAll().stream()
                .map(this::convertirCuotasDTO)
                .collect(Collectors.toList());
    }

    public CuotasDTO obtenerCuotaPorId(Integer id) {
        return cuotasRepository.findById(id)
                .map(this::convertirCuotasDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cuota no encontrada"));
    }

    public CuotasDTO actualizarCuota(Integer id, CuotasDTO cuotasDTO) {
        Cuotas cuotas = cuotasRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cuota no encontrada"));

        cuotas.setPrCuota(cuotasDTO.getPrCuota());
        cuotas.setFeModificacion(cuotasDTO.getFeModificacion());
        if (cuotasDTO.getIdAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(cuotasDTO.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado"));
            cuotas.setAlumno(alumno);
        }

        cuotas = cuotasRepository.save(cuotas);

        return convertirCuotasDTO(cuotas);
    }

    public void eliminarCuota(Integer id) {
        if (cuotasRepository.existsById(id)) {
            cuotasRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cuota no encontrada");
        }
    }

    private CuotasDTO convertirCuotasDTO(Cuotas cuota) {
        CuotasDTO dto = new CuotasDTO();
        dto.setId(cuota.getId());
        dto.setIdAlumno(cuota.getAlumno().getId());
        dto.setPrCuota(cuota.getPrCuota());
        dto.setFeRegistro(cuota.getFeRegistro());
        dto.setFeModificacion(cuota.getFeModificacion());
        return dto;
    }
}
