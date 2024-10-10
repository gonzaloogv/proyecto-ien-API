package com.ien.ienapp.control;

import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.entity.MateriaTemaId;
import com.ien.ienapp.service.MateriaTemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/materias-temas")
public class MateriaTemaController {
    @Autowired
    private MateriaTemaService materiaTemaService;

    @GetMapping
    public List<MateriaTema> getAllMateriasTemas() {
        return materiaTemaService.getAllMateriasTemas();
    }

    @GetMapping("/{idMateria}/{idTema}")
    public ResponseEntity<MateriaTema> getMateriaTema(@PathVariable Long idMateria, @PathVariable Long idTema) {
        MateriaTemaId id = new MateriaTemaId(idMateria, idTema);
        MateriaTema materiaTema = materiaTemaService.getMateriaTemaById(id);
        return materiaTema != null ? ResponseEntity.ok(materiaTema) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MateriaTema> createMateriaTema(@RequestBody MateriaTema materiaTema) {
        // Aquí puedes crear la relación y guardarla en la base de datos
        MateriaTema newMateriaTema = materiaTemaService.createMateriaTema(materiaTema);
        return new ResponseEntity<>(newMateriaTema, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idMateria}/{idTema}")
    public ResponseEntity<Void> deleteMateriaTema(@PathVariable Long idMateria, @PathVariable Long idTema) {
        MateriaTemaId id = new MateriaTemaId(idMateria, idTema);
        materiaTemaService.deleteMateriaTema(id);
        return ResponseEntity.noContent().build();
    }
}
