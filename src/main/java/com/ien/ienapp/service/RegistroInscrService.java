package com.ien.ienapp.service;

import com.ien.ienapp.entity.RegistroInscr;
import com.ien.ienapp.repository.RegistroInscrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroInscrService {

    @Autowired
    private RegistroInscrRepository repository;

    // Obtener todos los registros
    public List<RegistroInscr> getAll() {
        return repository.findAll();
    }

    // Obtener registro por ID
    public Optional<RegistroInscr> getById(Long id) {
        return repository.findById(id);
    }

    // Crear nuevo registro
    public RegistroInscr create(RegistroInscr registroInscr) {
        return repository.save(registroInscr);
    }

    // Actualizar registro existente
    public RegistroInscr update(Long id, RegistroInscr registroInscr) {
        Optional<RegistroInscr> existing = repository.findById(id);
        if (existing.isPresent()) {
            RegistroInscr updated = existing.get();
            updated.setNombre(registroInscr.getNombre());
            updated.setApellido(registroInscr.getApellido());
            updated.setEmail(registroInscr.getEmail());
            updated.setFechaNacimiento(registroInscr.getFechaNacimiento()); // Cambiado de 'edad' a 'fechaNacimiento'
            updated.setDni(registroInscr.getDni()); // Cambiado a 'nuDni'
            updated.setDeDireccion(registroInscr.getDeDireccion()); // Cambiado a 'deDireccion'
            updated.setNuCelular(registroInscr.getNuCelular()); // Cambiado a 'nuCelular'
            updated.setNuTelefono(registroInscr.getNuTelefono()); // Cambiado a 'nuTelefono'
            updated.setPais(registroInscr.getPais());
            updated.setProvincia(registroInscr.getProvincia());
            updated.setLocalidad(registroInscr.getLocalidad());
            updated.setCarrera(registroInscr.getCarrera());
            updated.setGenero(registroInscr.getGenero());
            return repository.save(updated);
        }
        return null; // O lanza una excepción en lugar de devolver null
    }

    // Eliminar registro
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
