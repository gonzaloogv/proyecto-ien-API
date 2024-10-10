package com.ien.ienapp.control;

import com.ien.ienapp.entity.Carrera;
import com.ien.ienapp.service.CarreraService;
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
@RequestMapping("/api/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraService.getAllCarreras();
    }

    @GetMapping("/{id}")
    public Optional<Carrera> getCarreraById(@PathVariable Integer id) {
        return carreraService.getCarreraById(id);
    }

    @PostMapping
    public Carrera createCarrera(@RequestBody Carrera carrera) {
        return carreraService.createCarrera(carrera);
    }

    @PutMapping("/{id}")
    public Carrera updateCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) {
        carrera.setId(id);  // Asegúrate de establecer el ID en la entidad antes de actualizar
        return carreraService.createCarrera(carrera);  // Se reutiliza el mismo método para guardar o actualizar
    }

    @DeleteMapping("/{id}")
    public void deleteCarrera(@PathVariable Integer id) {
        carreraService.deleteCarrera(id);
    }
}
