package com.ien.ienapp.service;

import com.ien.ienapp.dto.PlanesEstudiosDTO;
import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.repository.PlanesEstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanesEstudiosService {

    @Autowired
    private PlanesEstudiosRepository planesEstudiosRepository;

    public List<PlanesEstudiosDTO> getAllPlanesEstudio() {
    List<PlanesEstudios> planesEstudio = planesEstudiosRepository.findAll(); // Llama al repositorio para obtener los planes
        return planesEstudio.stream()
            .map(plan -> {
                PlanesEstudiosDTO dto = new PlanesEstudiosDTO();
                dto.setId(plan.getId()); // Asegúrate de que PlanEstudio tiene un método getId()
                dto.setDePlan(plan.getDePlan());
                dto.setDeObservacion(plan.getDeObservacion());
                dto.setNuAnioPlan(plan.getNuAnioPlan());
                dto.setFeRegistro(plan.getFeRegistro());
                return dto;
            })
            .collect(Collectors.toList());
    }

    public List<PlanesEstudios> getAllPlanesEstudios() {
        return planesEstudiosRepository.findAll();
    }

    public Optional<PlanesEstudios> getPlanesEstudiosById(Integer id) {
        return planesEstudiosRepository.findById(id);
    }

    public PlanesEstudios createPlanesEstudios(PlanesEstudios planesEstudios) {
        return planesEstudiosRepository.save(planesEstudios);
    }

    public void deletePlanesEstudios(Integer id) {
        planesEstudiosRepository.deleteById(id);
    }
}
