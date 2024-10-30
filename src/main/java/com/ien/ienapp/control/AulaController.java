package com.ien.ienapp.control;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Aula;
import com.ien.ienapp.service.AulaService;

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
@RequestMapping("/api/aula")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @PostMapping
    public ResponseEntity<?> crearAula(@RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            Aula aula = aulaService.crearAula(comisionDetalleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Aula creado exitosamente");
            response.put("alumnoId", aula.getIdAula());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear el aula: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComisionDetalleDTO> obtenerAulas(@PathVariable Integer id) {
        Aula aula = aulaService.obtenerAulasPorId(id); // Obtener el objeto Alumno
        ComisionDetalleDTO comisionDetalleDTO = aulaService.convertirAulaDTO(aula); // Convertir Alumno a RRHHDTO
        return ResponseEntity.ok(comisionDetalleDTO);
    }

    @GetMapping
    public ResponseEntity<List<ComisionDetalleDTO>> obtenerAulas() {
        List<ComisionDetalleDTO> aula = aulaService.obtenerAulas();
        return ResponseEntity.ok(aula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAula(@PathVariable Integer id, @RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            aulaService.actualizarAula(id, comisionDetalleDTO);
            return ResponseEntity.ok("Alumno actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el aula: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAula(@PathVariable Integer id) {
        try {
            aulaService.eliminarAula(id);
            return ResponseEntity.ok("Aula eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el aula: " + e.getMessage());
        }
    }
}