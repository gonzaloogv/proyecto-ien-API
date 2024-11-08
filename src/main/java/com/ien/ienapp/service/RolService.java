package com.ien.ienapp.service;

import com.ien.ienapp.dto.RolesDTO;
import com.ien.ienapp.entity.Rol;
import com.ien.ienapp.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
   
    public Rol crearRol(RolesDTO rolesDTO) {
        Rol rol = new Rol();
        rol.setId(rol.getId());
        rol.setDeRol(rol.getDeRol());
        rol.setFeRegistro(rol.getFeRegistro());
        return rolRepository.save(rol);
    }

    public List<RolesDTO> obtenerRoles() {
        return rolRepository.findAll().stream()
                .map(this::convertirRolDTO)
                .collect(Collectors.toList());
    }

    public Optional<Rol>obtenerRolesPorID(Integer id) {
        return rolRepository.findById(id);
    }

    public RolesDTO convertirRolDTO(Rol rol) {
        RolesDTO dto = new RolesDTO();
        dto.setIdRol(rol.getId());
        dto.setDeRol(rol.getDeRol());
        dto.setFeRegistro(rol.getFeRegistro());
        dto.setFeModificacion(rol.getFeModificacion());

        return dto;
    }
}