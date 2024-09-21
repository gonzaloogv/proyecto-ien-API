package com.proyecto.api.controller;

import com.proyecto.api.exception.ResourceNotFoundException;
import com.proyecto.api.model.Profesores;
import com.proyecto.api.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RestController
@RequestMapping("/api/v1")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping("/profesores")
    public List<Profesores> listarProfesores() {
        return profesorRepository.findAll();
    }

    @PostMapping("/profesores")
    public Profesores guardarProfesor(@RequestBody Profesores profesores) {
        return profesorRepository.save(profesores);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<Profesores> buscarProfesorPorId(@PathVariable Long id) {
        Profesores profesores = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El profesor no fue encontrado"));
        return ResponseEntity.ok(profesores);
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesores> actualizarProfesor(@PathVariable Long id, @RequestBody Profesores profesoresRequest) {
        Profesores profesores = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El profesor no fue encontrado"));

        profesores.setNombre(profesoresRequest.getNombre());
        profesores.setApellido(profesoresRequest.getApellido());
        profesores.setDni(profesoresRequest.getDni());
        profesores.setEmail(profesoresRequest.getEmail());
        profesores.setPassword(profesoresRequest.getPassword());
        profesores.setCarrera(profesoresRequest.getCarrera());

        Profesores profesoresActualizado = profesorRepository.save(profesores);
        return ResponseEntity.ok(profesoresActualizado);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProfesor(@PathVariable Long id) {
        Profesores profesores = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El profesor no fue encontrado"));

        profesorRepository.delete(profesores);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
