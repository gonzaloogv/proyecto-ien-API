package com.ien.ienapp.control;

import com.ien.ienapp.dto.RRHHDTO;
import com.ien.ienapp.entity.Alumno;
import com.ien.ienapp.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<?> crearAlumno(@RequestBody RRHHDTO rrhhDTO) {
        try {
            Alumno alumno = alumnoService.crearAlumno(rrhhDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Alumno creado exitosamente");
            response.put("alumnoId", alumno.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear el alumno: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RRHHDTO> obtenerAlumno(@PathVariable Integer id) {
        Alumno alumno = alumnoService.obtenerAlumnoPorId(id); // Obtener el objeto Alumno
        RRHHDTO rrhhDTO = alumnoService.convertirAAlumnoDTO(alumno); // Convertir Alumno a RRHHDTO
        return ResponseEntity.ok(rrhhDTO);
    }


    @GetMapping
    public ResponseEntity<List<RRHHDTO>> obtenerTodosLosAlumnos() {
        List<RRHHDTO> alumnos = alumnoService.obtenerTodosLosAlumnos();
        return ResponseEntity.ok(alumnos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAlumno(@PathVariable Integer id, @RequestBody RRHHDTO rrhhDTO) {
        try {
            alumnoService.actualizarAlumno(id, rrhhDTO);
            return ResponseEntity.ok("Alumno actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el alumno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAlumno(@PathVariable Integer id) {
        try {
            alumnoService.eliminarAlumno(id);
            return ResponseEntity.ok("Alumno eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el alumno: " + e.getMessage());
        }
    }
}
