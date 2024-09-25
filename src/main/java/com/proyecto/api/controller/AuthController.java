package com.proyecto.api.controller;

import com.proyecto.api.dto.LoginRequest;
import com.proyecto.api.model.Alumnos;
import com.proyecto.api.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping("/login")  // Cambia la ruta si es necesario
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        String usuario = loginRequest.getUsuario();
        String password = loginRequest.getPassword();

        // Obtener el alumno por su usuario
        Alumnos alumno = alumnoRepository.findByUsuario(usuario);

        // Preparamos la respuesta
        Map<String, Object> response = new HashMap<>();

        // Validamos las credenciales
        if (alumno != null && alumno.getPassword().equals(password)) {
            response.put("message", "Login exitoso");
            response.put("rol", alumno.getRol()); // Aquí obtenemos el rol del alumno
            response.put("usuario", alumno.getUsuario()); // También puedes enviar el usuario si es necesario
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("error", "Credenciales incorrectas"));
        }
    }
}

