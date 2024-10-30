package com.ien.ienapp.control;

import com.ien.ienapp.dto.ComisionDetalleDTO;
import com.ien.ienapp.entity.Horario;
import com.ien.ienapp.service.HorarioService;

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
@RequestMapping("/api/horario")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping
    public ResponseEntity<?> crearHorario(@RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            Horario horario = horarioService.crearHorario(comisionDetalleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Horario creado exitosamente");
            response.put("IdHorario", horario.getIdHorario());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Error de restricción: " + e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Error de integridad de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al crear el horario: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComisionDetalleDTO> obtenerHorario(@PathVariable Integer id) {
        try {
            Horario horario = horarioService.obtenerHorarioId(id);
            ComisionDetalleDTO comisionDetalleDTO = horarioService.convertirHorarioDTO(horario);
            return ResponseEntity.ok(comisionDetalleDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ComisionDetalleDTO>> obtenerHorarios() {
        List<ComisionDetalleDTO> horario = horarioService.obtenerHorarios();
        return ResponseEntity.ok(horario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actHorario(@PathVariable Integer id, @RequestBody ComisionDetalleDTO comisionDetalleDTO) {
        try {
            horarioService.actHorario(id, comisionDetalleDTO);
            return ResponseEntity.ok("Horario actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el horario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHorario(@PathVariable Integer id) {
        try {
            horarioService.eliminarHorario(id);
            return ResponseEntity.ok("Horario eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el horario: " + e.getMessage());
        }
    }
}
