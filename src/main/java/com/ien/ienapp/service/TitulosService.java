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

    // Método para crear un título desde ProfesoresDTO
    public Titulos crearTitulo(ProfesoresDTO profesoresDTO) {
        Titulos titulos = new Titulos();
        titulos.setId(profesoresDTO.getIdTitulo());
        titulos.setDeTitulo(profesoresDTO.getDeTitulo());
        titulos.setFeRegistro(profesoresDTO.getFeRegistro());
        titulos.setFeModificacion(profesoresDTO.getFeModificacion());
        return titulosRepository.save(titulos);
    }

    // Método para obtener todos los títulos como ProfesoresDTO
    public List<ProfesoresDTO> obtenerTitulos() {
        return titulosRepository.findAll().stream()
                .map(this::convertirTitulosAProfesorDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener un título por su ID
    public Optional<Titulos> obtenerTitulosPorId(Integer id) {
        return titulosRepository.findById(id);
    }

    // Método para actualizar un título
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

    // Método para eliminar un título
    public void eliminarTitulo(Integer id) {
        if (titulosRepository.existsById(id)) {
            titulosRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Titulo no encontrado");
        }
    }

    // Método para convertir Titulos a ProfesoresDTO
    public ProfesoresDTO convertirTitulosAProfesorDTO(Titulos titulos) {
        ProfesoresDTO dto = new ProfesoresDTO();
        dto.setIdTitulo(titulos.getId());
        dto.setDeTitulo(titulos.getDeTitulo());
        dto.setFeRegistro(titulos.getFeRegistro());
        dto.setFeModificacion(titulos.getFeModificacion());
        // Aquí puedes agregar otros campos si es necesario (por ejemplo, imagen del título o cargo del profesor)
        return dto;
    }
}
