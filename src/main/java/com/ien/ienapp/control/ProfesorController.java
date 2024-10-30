package com.ien.ienapp.control;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.Profesor;
import com.ien.ienapp.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<?> crearProfesor(@RequestBody RRHHDTO rrhhDTO) {
        try {
            Profesor profesor = profesorService.crearProfesor(rrhhDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Profesor creado exitosamente");
            response.put("profesorId", profesor.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear el alumno: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<RRHHDTO>> obtenerTodosLosProfesores() {
        List<RRHHDTO> profesor = profesorService.obtenerTodosLosProfesores();
        return ResponseEntity.ok(profesor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RRHHDTO> obtenerProfesor(@PathVariable Integer id) {
        RRHHDTO rrhhDTO = profesorService.obtenerProfesorPorId(id);
        return ResponseEntity.ok(rrhhDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProfesor(@PathVariable Integer id, @RequestBody RRHHDTO rrhhDTO) {
        try {
            profesorService.actualizarProfesor(id, rrhhDTO);
            return ResponseEntity.ok("Profesor actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el profesor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProfesor(@PathVariable Integer id) {
        try {
            profesorService.eliminarProfesor(id);
            return ResponseEntity.ok("Profesor eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el profesor: " + e.getMessage());
        }
    }
}
