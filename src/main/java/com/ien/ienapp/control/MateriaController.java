package com.ien.ienapp.control;

import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/materias")
public class MateriaController {
    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateria(@PathVariable Integer id) {
        Materia materia = materiaService.getMateriaById(id);
        return materia != null ? ResponseEntity.ok(materia) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.createMateria(materia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable Integer id, @RequestBody Materia materia) {
        Materia updatedMateria = materiaService.updateMateria(id, materia);
        return updatedMateria != null ? ResponseEntity.ok(updatedMateria) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Integer id) {
        materiaService.deleteMateria(id);
        return ResponseEntity.noContent().build();
    }
}
