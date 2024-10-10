package com.ien.ienapp.control;


import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.service.PlanesEstudiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/planes-estudios")
public class PlanesEstudiosController {

    @Autowired
    private PlanesEstudiosService planesEstudiosService;

    @GetMapping
    public List<PlanesEstudios> getAllPlanesEstudios() { return planesEstudiosService.getAllPlanesEstudios(); }

    @GetMapping("/{id}")
    public Optional<PlanesEstudios> getPlanesEstudiosById(@PathVariable Integer id) { return planesEstudiosService.getPlanesEstudiosById(id); }

    @PostMapping
    public PlanesEstudios planesEstudios(@RequestBody PlanesEstudios planesEstudios) {
        return planesEstudiosService.createPlanesEstudios(planesEstudios);
    }

    @DeleteMapping("/{id}")
    public void deletePlanesEstudios(@PathVariable Integer id) {
        planesEstudiosService.deletePlanesEstudios(id);
    }
}
