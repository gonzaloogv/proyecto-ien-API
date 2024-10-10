package com.ien.ienapp.control;

import com.ien.ienapp.Error.ErrorResponse;
import com.ien.ienapp.entity.RegistroInscr;
import com.ien.ienapp.service.RegistroInscrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/registro-inscripciones")
public class RegistroInscrController {

    @Autowired
    private RegistroInscrService service;

    @GetMapping
    public List<RegistroInscr> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroInscr> getById(@PathVariable Long id) {
        Optional<RegistroInscr> registroInscr = service.getById(id);
        return registroInscr.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegistroInscr registroInscr) {
        try {
            RegistroInscr createdRegistro = service.create(registroInscr);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRegistro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Error al crear el registro: " + e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<RegistroInscr> update(@PathVariable Long id, @RequestBody RegistroInscr registroInscr) {
        RegistroInscr updated = service.update(id, registroInscr);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
