package com.ien.ienapp.service;

import com.ien.ienapp.dto.CarreraDTO;
import com.ien.ienapp.entity.Carrera;
import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.repository.CarreraRepository;
import com.ien.ienapp.repository.PlanesEstudiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private PlanesEstudiosRepository planesEstudiosRepository; // Repositorio para PlanesEstudios

    @Transactional
    public CarreraDTO crearCarrera(CarreraDTO carreraDTO) {
        Carrera carrera = new Carrera();
        carrera.setDeCarrera(carreraDTO.getDeCarrera());
        carrera.setDeSede(carreraDTO.getDeSede());
        carrera.setNuDuracionHoras(carreraDTO.getNuDuracionHoras());
        carrera.setDeResponsable(carreraDTO.getDeResponsable());
        carrera.setTiCarrera(carreraDTO.getTiCarrera());
        carrera.setFeRegistro(new Date());

        // Manejo del ID de planesEstudio
        if (carreraDTO.getPlanesEstudioId() != null) { // Cambiado aquí
            Optional<PlanesEstudios> planesEstudios = planesEstudiosRepository.findById(carreraDTO.getPlanesEstudioId());
            if (planesEstudios.isPresent()) {
                carrera.setPlanesEstudio(planesEstudios.get());
            } else {
                throw new RuntimeException("Plan de estudio no encontrado");
            }
        }

        // Guardar la carrera
        Carrera nuevaCarrera = carreraRepository.save(carrera);

        // Convertir a DTO
        CarreraDTO resultDTO = new CarreraDTO();
        resultDTO.setId(nuevaCarrera.getId()); // Añadir ID de carrera
        resultDTO.setDeCarrera(nuevaCarrera.getDeCarrera());
        resultDTO.setDeSede(nuevaCarrera.getDeSede());
        resultDTO.setNuDuracionHoras(nuevaCarrera.getNuDuracionHoras());
        resultDTO.setDeResponsable(nuevaCarrera.getDeResponsable());
        resultDTO.setTiCarrera(nuevaCarrera.getTiCarrera());
        resultDTO.setFeRegistro(nuevaCarrera.getFeRegistro());
        resultDTO.setPlanesEstudioId(nuevaCarrera.getPlanesEstudio().getId()); // Agregar ID del Plan de Estudio al DTO

        return resultDTO;
    }

    // Método para obtener todas las carreras
    public List<CarreraDTO> getAllCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        return carreras.stream()
                .map(carrera -> {
                    CarreraDTO dto = new CarreraDTO();
                    dto.setId(carrera.getId());
                    dto.setDeCarrera(carrera.getDeCarrera());
                    dto.setDeSede(carrera.getDeSede());
                    dto.setNuDuracionHoras(carrera.getNuDuracionHoras());
                    dto.setDeResponsable(carrera.getDeResponsable());
                    dto.setTiCarrera(carrera.getTiCarrera());
                    dto.setPlanesEstudioId(carrera.getPlanesEstudio().getId()); // Cambiado aquí
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Obtener una carrera por ID
    public Optional<CarreraDTO> obtenerCarreraPorId(Integer id) {
        return carreraRepository.findById(id).map(carrera -> {
            CarreraDTO carreraDTO = new CarreraDTO();
            carreraDTO.setId(carrera.getId()); // Añadir ID de carrera
            carreraDTO.setDeCarrera(carrera.getDeCarrera());
            carreraDTO.setDeSede(carrera.getDeSede());
            carreraDTO.setNuDuracionHoras(carrera.getNuDuracionHoras());
            carreraDTO.setDeResponsable(carrera.getDeResponsable());
            carreraDTO.setTiCarrera(carrera.getTiCarrera());
            carreraDTO.setFeRegistro(carrera.getFeRegistro());
            carreraDTO.setPlanesEstudioId(carrera.getPlanesEstudio().getId()); // Cambiado aquí
            return carreraDTO;
        });
    }

    // Actualizar una carrera
    @Transactional
    public CarreraDTO actualizarCarrera(Integer id, CarreraDTO carreraDTO) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {
            Carrera carrera = optionalCarrera.get();
            carrera.setDeCarrera(carreraDTO.getDeCarrera());
            carrera.setDeSede(carreraDTO.getDeSede());
            carrera.setNuDuracionHoras(carreraDTO.getNuDuracionHoras());
            carrera.setDeResponsable(carreraDTO.getDeResponsable());
            carrera.setTiCarrera(carreraDTO.getTiCarrera());
            carrera.setFeModificacion(new Date());

            // Manejo de Planes de Estudio por ID
            if (carreraDTO.getPlanesEstudioId() != null) { // Cambiado aquí
                Optional<PlanesEstudios> planesEstudios = planesEstudiosRepository.findById(carreraDTO.getPlanesEstudioId());
                planesEstudios.ifPresent(carrera::setPlanesEstudio);
            }

            carreraRepository.save(carrera);
            return carreraDTO;
        }
        return null; // o lanzar una excepción si no se encuentra
    }

    // Eliminar una carrera
    @Transactional
    public void eliminarCarrera(Integer id) {
        carreraRepository.deleteById(id);
    }
}
