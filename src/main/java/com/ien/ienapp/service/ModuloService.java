package com.ien.ienapp.service;

import com.ien.ienapp.dto.ModuloDTO;
import com.ien.ienapp.entity.Modulo;
import com.ien.ienapp.repository.ModuloRepository;
import com.ien.ienapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    // Método para crear un nuevo módulo
    public ModuloDTO crearModulo(ModuloDTO moduloDTO) {
        Modulo modulo = new Modulo();
        modulo.setDeModulo(moduloDTO.getDeModulo());
        modulo.setFeRegistro(moduloDTO.getFeRegistro());
        modulo.setFeModificacion(moduloDTO.getFeModificacion());

        // Guardamos la entidad en la base de datos
        Modulo savedModulo = moduloRepository.save(modulo);

        // Convertimos la entidad a DTO y la devolvemos
        return convertirModuloDTO(savedModulo);
    }

    // Método para obtener todos los módulos
    public List<ModuloDTO> obtenerTodosLosModulos() {
        List<Modulo> modulos = moduloRepository.findAll();
        return modulos.stream()
                .map(this::convertirModuloDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener un módulo por ID
    public Optional<ModuloDTO> obtenerModuloPorId(Integer id) {
        Optional<Modulo> moduloOptional = moduloRepository.findById(id);
        return moduloOptional.map(this::convertirModuloDTO);
    }

    // Método para actualizar un módulo
    public ModuloDTO actualizarModulo(Integer id, ModuloDTO moduloDTO) {
        if (moduloRepository.existsById(id)) {
            Modulo modulo = new Modulo();
            modulo.setId(moduloDTO.getId());
            modulo.setDeModulo(moduloDTO.getDeModulo());
            modulo.setFeRegistro(moduloDTO.getFeRegistro());
            modulo.setFeModificacion(moduloDTO.getFeModificacion());

            Modulo updatedModulo = moduloRepository.save(modulo);
            return convertirModuloDTO(updatedModulo);
        } else {
            throw new ResourceNotFoundException("Modulo no encontrado");
        }
    }

    // Método para eliminar un módulo
    public boolean eliminarModulo(Integer id) {
        if (moduloRepository.existsById(id)) {
            moduloRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Método para convertir una entidad Modulo a un DTO
    private ModuloDTO convertirModuloDTO(Modulo modulo) {
        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setId(modulo.getId());
        moduloDTO.setDeModulo(modulo.getDeModulo());
        moduloDTO.setFeRegistro(modulo.getFeRegistro());
        moduloDTO.setFeModificacion(modulo.getFeModificacion());
        return moduloDTO;
    }
}
