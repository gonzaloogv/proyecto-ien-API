package com.ien.ienapp.control;

import com.ien.ienapp.entity.Paises;
import com.ien.ienapp.service.PaisesService;
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
@RequestMapping("/api/paises")
public class PaisesController {

    @Autowired
    private PaisesService paisesService;

    @GetMapping
    public List<Paises> getAllPaises() {
        return paisesService.getAllPaises();
    }

    @GetMapping("/{id}")
    public Optional<Paises> getPaisById(@PathVariable Long id) {
        return paisesService.getPaisById(id);
    }

    @PostMapping
    public Paises createPais(@RequestBody Paises pais) {
        return paisesService.createPais(pais);
    }

    @PutMapping("/{id}")
    public Paises updatePais(@PathVariable Long id, @RequestBody Paises pais) {
        return paisesService.updatePais(id, pais);
    }

    @DeleteMapping("/{id}")
    public void deletePais(@PathVariable Long id) {
        paisesService.deletePais(id);
    }
}
