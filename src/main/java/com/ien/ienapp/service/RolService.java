package com.ien.ienapp.service;

import com.ien.ienapp.entity.Rol;
import com.ien.ienapp.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> getRolById(Integer id) {
        return rolRepository.findById(id);
    }

    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol updateRol(Integer id, Rol rol) {
        if (rolRepository.existsById(id)) {
            rol.setId(id);
            return rolRepository.save(rol);
        }
        return null;
    }

    public void deleteRol(Integer id) {
        rolRepository.deleteById(id);
    }
}
