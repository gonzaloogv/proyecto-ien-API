package com.ien.ienapp.control;

import com.ien.ienapp.entity.EstadoCivil;
import com.ien.ienapp.service.EstadoCivilService;
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
@RequestMapping("/api/estado-civil")
public class EstadoCivilController {

    @Autowired
    private EstadoCivilService estadoCivilService;

    @GetMapping
    public List<EstadoCivil> getAllEstadoCivil() {
        return estadoCivilService.getAllEstadoCivil();
    }

    @GetMapping("/{id}")
    public Optional<EstadoCivil> getEstadoCivilById(@PathVariable Integer id) {
        return estadoCivilService.getEstadoCivilById(id);
    }

    @PostMapping
    public EstadoCivil createEstadoCivil(@RequestBody EstadoCivil estadoCivil) {
        return estadoCivilService.createEstadoCivil(estadoCivil);  // Se delega la creación al servicio
    }

    @PutMapping("/{id}")
    public EstadoCivil updateEstadoCivil(@PathVariable Integer id, @RequestBody EstadoCivil estadoCivil) {
        estadoCivil.setId(id);  // Asegúrate de establecer el ID en la entidad antes de actualizar
        return estadoCivilService.createEstadoCivil(estadoCivil);  // Se reutiliza el mismo método para guardar o actualizar
    }


    @DeleteMapping("/{id}")
    public void deleteEstadoCivil(@PathVariable Integer id) {
        estadoCivilService.deleteEstadoCivil(id);
    }
}
