package com.ien.ienapp.control;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Comision;
import com.ien.ienapp.service.ComisionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comision")
public class ComisionController {

    @Autowired
    private ComisionService comisionService;

    @PostMapping
    public ResponseEntity<?> crearAula(@Valid @RequestBody ComisionDetalleDTO comisionDetalleDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> filteredErrors = result.getFieldErrors().stream()
                    .filter(fieldError -> 
                        fieldError.getField().equals("deDescripcion") ||
                        fieldError.getField().equals("nuCodigoComision") ||
                        fieldError.getField().equals("nuAnioDeMateria")
                    )
                    .map(fieldError -> fieldError.getDefaultMessage()) // Obtiene el mensaje de cada error
                    .collect(Collectors.toList());
    
            if (!filteredErrors.isEmpty()) {
                return ResponseEntity.badRequest().body(filteredErrors);
            }
        }
    
        try {
            Comision comision = comisionService.crearComision(comisionDetalleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Comisión creada exitosamente");
            response.put("comisionId", comision.getIdComision());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear la comisión: " + e.getMessage()));
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