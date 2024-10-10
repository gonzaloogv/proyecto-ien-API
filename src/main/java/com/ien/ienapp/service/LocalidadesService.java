package com.ien.ienapp.service;

import com.ien.ienapp.entity.Localidades;
import com.ien.ienapp.repository.LocalidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class LocalidadesService {

    @Autowired
    private LocalidadesRepository localidadesRepository;

    // Método para obtener todas las localidades
    public List<Localidades> getAllLocalidades() {
        return localidadesRepository.findAll();
    }

    // Método para obtener una localidad por ID
    public Optional<Localidades> getLocalidadesById(Integer id) {
        return localidadesRepository.findById(id);
    }

    // Método para crear una nueva localidad
    public Localidades createLocalidad(Localidades localidad) {
        return localidadesRepository.save(localidad);
    }

    // Método para actualizar una localidad existente
    public Localidades updateLocalidad(Integer id, Localidades localidad) {
        Optional<Localidades> existingLocalidad = localidadesRepository.findById(id);
        if (existingLocalidad.isPresent()) {
            Localidades updatedLocalidad = existingLocalidad.get();
            updatedLocalidad.setLocalidad(localidad.getLocalidad());
            updatedLocalidad.setProvincias(localidad.getProvincias()); // Asegúrate de que el objeto Provincia esté correctamente establecido
            updatedLocalidad.setFeModificacion(localidad.getFeModificacion());
            return localidadesRepository.save(updatedLocalidad);
        }
        return null; // O puedes lanzar una excepción si prefieres
    }

    // Método para eliminar una localidad
    public void deleteLocalidad(Integer id) {
        if (!localidadesRepository.existsById(id)) {
            throw new EntityNotFoundException("Localidad no encontrada con id: " + id);
        }
        localidadesRepository.deleteById(id);
    }

    // Método adicional para obtener localidades por provincia (nuevo)
    public List<Localidades> getLocalidadesByProvinciaId(Integer provinciaId) {
        return localidadesRepository.findByProvinciasId(provinciaId);
    }
}
