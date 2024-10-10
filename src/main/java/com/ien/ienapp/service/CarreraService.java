package com.ien.ienapp.service;

import com.ien.ienapp.entity.Carrera;
import com.ien.ienapp.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    public Optional<Carrera> getCarreraById(Integer id) {
        return carreraRepository.findById(id);
    }

    public Carrera createCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteCarrera(Integer id) {
        carreraRepository.deleteById(id);
    }
}
