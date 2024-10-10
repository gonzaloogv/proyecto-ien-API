package com.ien.ienapp.control;

import com.ien.ienapp.entity.Provincias;
import com.ien.ienapp.service.ProvinciasService;
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
@RequestMapping("/api/provincias")
public class ProvinciasController {

    @Autowired
    private ProvinciasService provinciasService;

    @GetMapping
    public List<Provincias> getAllProvincias() {
        return provinciasService.getAllProvincias();
    }

    @GetMapping("/{id}")
    public Optional<Provincias> getProvinciaById(@PathVariable Long id) {
        return provinciasService.getProvinciaById(id);
    }

    @PostMapping
    public Provincias createProvincia(@RequestBody Provincias provincia) {
        return provinciasService.createProvincia(provincia);
    }

    @PutMapping("/{id}")
    public Provincias updateProvincia(@PathVariable Long id, @RequestBody Provincias provincia) {
        return provinciasService.updateProvincia(id, provincia);
    }

    @DeleteMapping("/{id}")
    public void deleteProvincia(@PathVariable Long id) {
        provinciasService.deleteProvincia(id);
    }
}
