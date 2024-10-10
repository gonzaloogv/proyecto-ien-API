package com.ien.ienapp.service;

import com.ien.ienapp.entity.Rol;
import com.ien.ienapp.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol createRol(Rol rol) {
        rol.setFeRegistro(LocalDateTime.now()); // Asignar fecha de registro
        return rolRepository.save(rol);
    }

    public Rol updateRol(Long id, Rol rol) {
        if (rolRepository.existsById(id)) {
            rol.setId(id);
            rol.setFeModificacion(LocalDateTime.now()); // Actualizar fecha de modificación
            return rolRepository.save(rol);
        }
        return null;
    }

    public void deleteRol(Long id) {
        rolRepository.deleteById(id);
    }
}
