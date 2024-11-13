package com.ien.ienapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.Administrativo;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.repository.AdminRepository;

@Service

public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public RRHHDTO convertirAAdminDTO(Administrativo administrativo) {
        RRHHDTO dto = new RRHHDTO();
        dto.setId(administrativo.getId());
        dto.setFeIngreso(administrativo.getFeIngreso());
        dto.setFeRegistro(administrativo.getFeRegistro());
        dto.setFeBaja(administrativo.getFeBaja());
        dto.setFeModificacion(administrativo.getFeModificacion());
        return dto;
    }

    public Administrativo crearAdministrativo(RRHHDTO rrhhDTO) {
        Administrativo administrativo = new Administrativo();
        administrativo.setId(rrhhDTO.getId());
        administrativo.setFeRegistro(rrhhDTO.getFeRegistro());
        administrativo.setFeIngreso(rrhhDTO.getFeIngreso());
        administrativo.setFeBaja(rrhhDTO.getFeBaja());
        administrativo.setFeBaja(rrhhDTO.getFeBaja());
        
        return adminRepository.save(administrativo);
    }
     public List<RRHHDTO> obtenerAdmin() {
        return adminRepository.findAll().stream()
                .map(this::convertirAAdminDTO)
                .collect(Collectors.toList());
    }

    public Administrativo obtAdminPorID(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin no encontrado"));
    }    

    public Administrativo actualizarAdministrativo(Integer id, RRHHDTO rrhhDTO) {
        if (adminRepository.existsById(id)) {
            Administrativo administrativo = new Administrativo();

            administrativo.setId(id);
            administrativo.setFeBaja(rrhhDTO.getFeBaja());
            administrativo.setFeIngreso(rrhhDTO.getFeIngreso());
            administrativo.setFeModificacion(rrhhDTO.getFeModificacion());
            return adminRepository.save(administrativo);
        }
        throw new ResourceNotFoundException("Admin no encontrado");
    }

    public void eliminarAdmin(Integer id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Admin no encontrado");
        }
    }
}
