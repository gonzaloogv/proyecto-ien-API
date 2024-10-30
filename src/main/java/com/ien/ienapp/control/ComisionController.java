package com.ien.ienapp.control;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Comision;
import com.ien.ienapp.service.ComisionService;

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
@RequestMapping("/api/comision")
public class ComisionController {

    @Autowired
    private ComisionService comisionService;

    @PostMapping
    public ResponseEntity<?> crearAula(@RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            Comision comision = comisionService.crearComision(comisionDetalleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comision creada exitosamente");
            response.put("alumnoId", comision.getIdComision());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear la comision: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComisionDetalleDTO> obtenerAulas(@PathVariable Integer id) {
        Comision comision = comisionService.obtenerComisionPorId(id); // Obtener el objeto Alumno
        ComisionDetalleDTO comisionDetalleDTO = comisionService.convertirComisionDTO(comision); // Convertir Alumno a RRHHDTO
        return ResponseEntity.ok(comisionDetalleDTO);
    }

    @GetMapping
    public ResponseEntity<List<ComisionDetalleDTO>> obtenerComision() {
        List<ComisionDetalleDTO> comision = comisionService.obtenerComision();
        return ResponseEntity.ok(comision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actComision(@PathVariable Integer id, @RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            comisionService.actComision(id, comisionDetalleDTO);
            return ResponseEntity.ok("Comision actualizada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la comision: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarComision(@PathVariable Integer id) {
        try {
            comisionService.eliminarComision(id);
            return ResponseEntity.ok("Comision eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la comision: " + e.getMessage());
        }
    }
}