package com.ien.ienapp.control;

import com.ien.ienapp.dto.TemaDTO;
import com.ien.ienapp.entity.Tema;
import com.ien.ienapp.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/temas")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @PostMapping
    public ResponseEntity<Tema> createTema(@RequestBody TemaDTO temaDTO) {
        if (temaDTO.getDeTitulo() == null || temaDTO.getDeDescripcion() == null || temaDTO.getFeRegistro() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Tema tema = new Tema();
        tema.setDeTitulo(temaDTO.getDeTitulo());
        tema.setDeDescripcion(temaDTO.getDeDescripcion());
        tema.setFeRegistro(temaDTO.getFeRegistro());

        Integer idMateria = temaDTO.getIdMateria(); 
        
        try {
            Tema createdTema = temaService.createTema(tema, idMateria);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTema);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getTemaById(@PathVariable Integer id) {
        Optional<Tema> tema = temaService.getTemaById(id);
        return tema.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping
    public ResponseEntity<Iterable<Tema>> getAllTemas() {
        return ResponseEntity.ok(temaService.getAllTemas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tema> updateTema(@PathVariable Integer id, @RequestBody Map<String, Object> payload) {
        if (!payload.containsKey("deTitulo") || !payload.containsKey("deDescripcion")) {
            return ResponseEntity.badRequest().body(null); // Datos faltantes
        }

        try {
            Tema tema = new Tema();
            tema.setDeTitulo((String) payload.get("deTitulo"));
            tema.setDeDescripcion((String) payload.get("deDescripcion"));
            Tema updatedTema = temaService.updateTema(id, tema);
            return ResponseEntity.ok(updatedTema);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTema(@PathVariable Integer id) {
        try {
            temaService.deleteTema(id);
            return ResponseEntity.noContent().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }
}
