package com.proyecto.api.controller;

import com.proyecto.api.exception.ResourceNotFoundException;
import com.proyecto.api.model.Alumnos;
import com.proyecto.api.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.api.dto.LoginRequest; // Asegúrate de importar la clase LoginRequest


import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RestController
@RequestMapping("/api/v1")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/alumnos")
    public List<Alumnos> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    @PostMapping("/alumnos")
    public Alumnos guardarAlumno(@RequestBody Alumnos alumnos) {
        return alumnoRepository.save(alumnos);
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumnos> buscarAlumnoPorId(@PathVariable Long id) {
        Alumnos alumnos = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El alumno no fue encontrado"));
        return ResponseEntity.ok(alumnos);
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Alumnos> actualizarAlumno(@PathVariable Long id, @RequestBody Alumnos alumnosRequest) {
        Alumnos alumnos = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El alumno no fue encontrado"));

        alumnos.setNombre(alumnosRequest.getNombre());
        alumnos.setApellido(alumnosRequest.getApellido());
        alumnos.setDni(alumnosRequest.getDni());
        alumnos.setEmail(alumnosRequest.getEmail());
        alumnos.setUsuario(alumnosRequest.getUsuario());
        alumnos.setPassword(alumnosRequest.getPassword());
        alumnos.setCarrera(alumnosRequest.getCarrera());

        Alumnos alumnosActualizado = alumnoRepository.save(alumnos);
        return ResponseEntity.ok(alumnosActualizado);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarAlumno(@PathVariable Long id) {
        Alumnos alumnos = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El alumno no fue encontrado"));

        alumnoRepository.delete(alumnos);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
