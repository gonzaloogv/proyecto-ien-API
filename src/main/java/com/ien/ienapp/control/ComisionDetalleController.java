package com.ien.ienapp.control;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.ComisionDetalle;
import com.ien.ienapp.service.ComisionDetalleService;

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
@RequestMapping("/api/comision-detalle")
public class ComisionDetalleController {

    @Autowired
    private ComisionDetalleService comisionDetalleService;

    @PostMapping
    public ResponseEntity<?> crearAula(@RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            ComisionDetalle comisionDetalle = comisionDetalleService.crearComisionDetalle(comisionDetalleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comision creada exitosamente");
            response.put("comisionDetalleId", comisionDetalle.getId());
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
        ComisionDetalle comisionDetalle = comisionDetalleService.obtenerComisionDetalleId(id); // Obtener el objeto Alumno
        ComisionDetalleDTO comisionDetalleDTO = comisionDetalleService.convertirComisionDetalleDTO(comisionDetalle); // Convertir Alumno a RRHHDTO
        return ResponseEntity.ok(comisionDetalleDTO);
    }
    
    @GetMapping("/materia/{idMateria}") 
    public ResponseEntity<List<ComisionDetalle>> getAlumnosByMateriaId(@PathVariable Integer idMateria) { 
        try { List<ComisionDetalle> alumnos = comisionDetalleService.getAlumnosByMateriaId(idMateria); 
            return ResponseEntity.ok(alumnos); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        } 
    }

    @GetMapping
    public ResponseEntity<List<ComisionDetalleDTO>> obtenerComisionDetalle() {
        List<ComisionDetalleDTO> comisionDetalle = comisionDetalleService.obtenerComisionDetalle();
        return ResponseEntity.ok(comisionDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarComisionDetalle(@PathVariable Integer id) {
        try {
            comisionDetalleService.eliminarComisionDetalle(id);
            return ResponseEntity.ok("ComisionD eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la comisionD: " + e.getMessage());
        }
    }
}