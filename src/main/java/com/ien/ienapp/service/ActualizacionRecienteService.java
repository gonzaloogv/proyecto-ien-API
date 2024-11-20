package com.ien.ienapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ien.ienapp.entity.ActualizacionReciente;
import com.ien.ienapp.repository.ActualizacionRecienteRepository;

import java.util.List;

@Service
public class ActualizacionRecienteService {

    @Autowired
    private ActualizacionRecienteRepository repository;

    public List<ActualizacionReciente> obtenerActualizacionesRecientes() {
        return repository.findAll();
    }

    public ActualizacionReciente guardarActualizacionReciente(ActualizacionReciente actualizacion) {
        return repository.save(actualizacion);
    }
}
