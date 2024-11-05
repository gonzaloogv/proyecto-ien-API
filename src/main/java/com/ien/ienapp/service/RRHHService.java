package com.ien.ienapp.service;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.CategoriaRrhh;
import com.ien.ienapp.entity.EstadoCivil;
import com.ien.ienapp.entity.Localidades;
import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.entity.RRHH;
import com.ien.ienapp.repository.CategoriaRrhhRepository;
import com.ien.ienapp.repository.EstadoCivilRepository;
import com.ien.ienapp.repository.LocalidadesRepository;
import com.ien.ienapp.repository.ProfesorRepository;
import com.ien.ienapp.repository.RRHHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RRHHService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private RRHHRepository rrhhRepository;

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    @Autowired
    private CategoriaRrhhRepository categoriaRrhhRepository;

    @Autowired
    private LocalidadesRepository localidadesRepository;


    public RRHHDTO convertToDTO(RRHH rrhh) {
        RRHHDTO dto = new RRHHDTO();
        dto.setId(rrhh.getId());
        dto.setNuDni(rrhh.getNuDni());
        dto.setDeNombre(rrhh.getDeNombre());
        dto.setDeApellido(rrhh.getDeApellido());
        dto.setNuCelular1(rrhh.getNuCelular1());
        dto.setNuCelular2(rrhh.getNuCelular2());
        dto.setNuCelular3(rrhh.getNuCelular3());
        dto.setNuTelefono1(rrhh.getNuTelefono1());
        dto.setNuTelefono2(rrhh.getNuTelefono2());
        dto.setNuTelefono3(rrhh.getNuTelefono3());
        dto.setDeDireccion(rrhh.getDeDireccion());
        dto.setDeGenero(rrhh.getDeGenero());
        dto.setIdEstadoCivil(rrhh.getEstadoCivil().getId());
        dto.setIdCategoriaRrhh(rrhh.getCategoriaRrhh().getId());
        dto.setIdLocalidad(rrhh.getLocalidad().getId());
        dto.setDeMail(rrhh.getDeMail());
        dto.setFeNacimiento(rrhh.getFeNacimiento());
        dto.setFeRegistro(rrhh.getFeRegistro());
        dto.setFeModificacion(rrhh.getFeModificacion());
        return dto;
    }

    public Optional<Profesor> getProfesorById(Integer id) {
        return profesorRepository.findById(id); // Suponiendo que tienes un repositorio para Profesor
    }

    // Convertir RRHHDTO a RRHH
    public RRHH convertToEntity(RRHHDTO dto) {
        RRHH rrhh = new RRHH();
        dto.setId(rrhh.getId());
        rrhh.setNuDni(dto.getNuDni());
        rrhh.setDeNombre(dto.getDeNombre());
        rrhh.setDeApellido(dto.getDeApellido());
        rrhh.setNuCelular1(dto.getNuCelular1());
        rrhh.setNuCelular2(dto.getNuCelular2());
        rrhh.setNuCelular3(dto.getNuCelular3());
        rrhh.setNuTelefono1(dto.getNuTelefono1());
        rrhh.setNuTelefono2(dto.getNuTelefono2());
        rrhh.setNuTelefono3(dto.getNuTelefono3());
        rrhh.setDeDireccion(dto.getDeDireccion());
        rrhh.setDeGenero(dto.getDeGenero());

        EstadoCivil estadoCivil = estadoCivilRepository.findById(dto.getIdEstadoCivil())
                .orElseThrow(() -> new RuntimeException("Estado Civil no encontrado con ID: " + dto.getIdEstadoCivil()));
        rrhh.setEstadoCivil(estadoCivil);

        CategoriaRrhh categoriaRrhh = categoriaRrhhRepository.findById(dto.getIdCategoriaRrhh())
                .orElseThrow(() -> new RuntimeException("Categoría RRHH no encontrada con ID: " + dto.getIdCategoriaRrhh()));
        rrhh.setCategoriaRrhh(categoriaRrhh);

        Localidades localidades = localidadesRepository.findById(dto.getIdLocalidad())
                .orElseThrow(() -> new RuntimeException("Localidad no encontrada con ID: " + dto.getIdLocalidad()));
        rrhh.setLocalidad(localidades);

        rrhh.setDeMail(dto.getDeMail());
        rrhh.setFeNacimiento(dto.getFeNacimiento());
        rrhh.setFeRegistro(dto.getFeRegistro());
        rrhh.setFeModificacion(dto.getFeModificacion());

        return rrhh;
    }

    public List<RRHH> getAllRRHH() {
        return rrhhRepository.findAll();
    }

    public Optional<RRHH> getRRHHById(Integer id) {
        return rrhhRepository.findById(id);
    }

    public RRHH createRRHH(RRHH rrhh) {
        if (rrhhRepository.existsByNuDni(rrhh.getNuDni())) {
            throw new RuntimeException("El DNI ya existe."); // Puedes lanzar una excepción personalizada
        }
        return rrhhRepository.save(rrhh);
    }

    public void deleteRRHH(Integer id) {
        rrhhRepository.deleteById(id);
    }
}
