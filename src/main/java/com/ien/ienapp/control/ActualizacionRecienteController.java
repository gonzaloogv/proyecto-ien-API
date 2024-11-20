package com.ien.ienapp.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ien.ienapp.entity.ActualizacionReciente;
import com.ien.ienapp.service.ActualizacionRecienteService;

import java.util.List;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ActualizacionRecienteController {

    @Autowired
    private ActualizacionRecienteService service;

    @GetMapping("/recientes")
    public List<ActualizacionReciente> getActualizacionesRecientes() {
        return service.obtenerActualizacionesRecientes();
    }

    @PostMapping("/recientes")
    public ActualizacionReciente registrarActualizacionReciente(@RequestBody ActualizacionReciente actualizacion) {
        actualizacion.setDate(new Date());  // Ajusta la fecha de registro
        return service.guardarActualizacionReciente(actualizacion);
    }
}
