package com.ien.ienapp.control;

import com.ien.ienapp.entity.Localidades;
import com.ien.ienapp.service.LocalidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/localidades")
public class LocalidadesController {

    @Autowired
    private LocalidadesService localidadesService;

    @GetMapping
    public List<Localidades> getAllLocalidades() {
        return localidadesService.getAllLocalidades();
    }

    @GetMapping("/{id}")
    public Optional<Localidades> getLocalidadesById(@PathVariable Integer id) {
        return localidadesService.getLocalidadesById(id);
    }

    @PostMapping
    public Localidades createLocalidades(@RequestBody Localidades localidad) {
        return localidadesService.createLocalidad(localidad);
    }

    @PutMapping("/{id}")
    public Localidades updateLocalidad(@PathVariable Integer id, @RequestBody Localidades localidad) {
        return localidadesService.updateLocalidad(id, localidad);
    }

    @DeleteMapping("/{id}")
    public void deleteLocalidad(@PathVariable Integer id) {
        localidadesService.deleteLocalidad(id);
    }
}
