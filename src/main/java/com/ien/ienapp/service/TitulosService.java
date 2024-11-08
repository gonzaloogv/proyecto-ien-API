package com.ien.ienapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.ProfesoresDTO;  // Usando ProfesoresDTO
import com.ien.ienapp.entity.Titulos;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.TitulosRepository;

@Service
public class TitulosService {

    @Autowired
    private TitulosRepository titulosRepository;

    // Constructor con inyección de dependencias
    public TitulosService(TitulosRepository titulosRepository) {
        this.titulosRepository = titulosRepository;
    }

    public Titulos crearTitulo(ProfesoresDTO profesoresDTO) {
        Titulos titulos = new Titulos();
        titulos.setId(profesoresDTO.getIdTitulo());
        titulos.setDeTitulo(profesoresDTO.getDeTitulo());
        titulos.setFeRegistro(profesoresDTO.getFeRegistro());
        titulos.setFeModificacion(profesoresDTO.getFeModificacion());
        return titulosRepository.save(titulos);
    }

    public List<ProfesoresDTO> obtenerTitulos() {
        return titulosRepository.findAll().stream()
                .map(this::convertirTitulosAProfesorDTO)
                .collect(Collectors.toList());
    }

    public Optional<Titulos> obtenerTitulosPorId(Integer id) {
        return titulosRepository.findById(id);
    }

    public Titulos actualizarTitulo(Integer id, ProfesoresDTO profesoresDTO) {
        if (titulosRepository.existsById(id)) {
            Titulos titulos = new Titulos();
            titulos.setId(id);
            titulos.setDeTitulo(profesoresDTO.getDeTitulo());
            titulos.setFeModificacion(profesoresDTO.getFeModificacion());
            return titulosRepository.save(titulos);
        }
        throw new ResourceNotFoundException("Titulo no encontrado");
    }

    public void eliminarTitulo(Integer id) {
        if (titulosRepository.existsById(id)) {
            titulosRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Titulo no encontrado");
        }
    }

    public ProfesoresDTO convertirTitulosAProfesorDTO(Titulos titulos) {
        ProfesoresDTO dto = new ProfesoresDTO();
        dto.setIdTitulo(titulos.getId());
        dto.setDeTitulo(titulos.getDeTitulo());
        dto.setFeRegistro(titulos.getFeRegistro());
        dto.setFeModificacion(titulos.getFeModificacion());

        return dto;
    }
}
