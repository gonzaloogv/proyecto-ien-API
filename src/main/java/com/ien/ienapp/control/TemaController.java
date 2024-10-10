package com.ien.ienapp.control;

import com.ien.ienapp.entity.Tema;
import com.ien.ienapp.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/temas")
public class TemaController {
    @Autowired
    private TemaService temaService;

    @GetMapping
    public List<Tema> getAllTemas() {
        return temaService.getAllTemas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getTema(@PathVariable Integer id) {
        Tema tema = temaService.getTemaById(id);
        return tema != null ? ResponseEntity.ok(tema) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Tema createTema(@RequestBody Tema tema) {
        return temaService.createTema(tema);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tema> updateTema(@PathVariable Integer id, @RequestBody Tema tema) {
        Tema updatedTema = temaService.updateTema(id, tema);
        return updatedTema != null ? ResponseEntity.ok(updatedTema) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTema(@PathVariable Integer id) {
        temaService.deleteTema(id);
        return ResponseEntity.noContent().build();
    }
}
