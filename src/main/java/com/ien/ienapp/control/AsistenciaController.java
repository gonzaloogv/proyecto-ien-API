package com.ien.ienapp.control;

import com.ien.ienapp.dto.AsistenciaDTO;
import com.ien.ienapp.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping
    public ResponseEntity<?> crearAsistencia(@Valid @RequestBody AsistenciaDTO asistenciaDTO) {
        try {
            AsistenciaDTO nuevaAsistencia = asistenciaService.crearAsistencia(asistenciaDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Asistencia creada exitosamente con ID: " + nuevaAsistencia.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al crear la asistencia: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAsistencias() {
        try {
            List<AsistenciaDTO> asistencias = asistenciaService.getAllAsistencias();
            return ResponseEntity.ok(asistencias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener asistencias: " + e.getMessage());
        }
    }

    @GetMapping("/comision-detalle/{idComisionDetalle}")
    public ResponseEntity<?> getAsistenciasByComisionDetalleId(@PathVariable Integer idComisionDetalle) {
        try {
            List<AsistenciaDTO> asistencias = asistenciaService.getAsistenciasByComisionDetalleId(idComisionDetalle);
            return ResponseEntity.ok(asistencias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener asistencias para ComisionDetalle ID " + idComisionDetalle + ": " + e.getMessage());
        }
    }

    // Otros endpoints, como actualizar, eliminar...
}
