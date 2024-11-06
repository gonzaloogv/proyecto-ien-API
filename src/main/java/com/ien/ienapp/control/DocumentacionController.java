package com.ien.ienapp.control;

import com.ien.ienapp.dto.DocumentacionDTO;
import com.ien.ienapp.entity.Documentacion;
import com.ien.ienapp.service.DocumentacionService;
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
@RequestMapping("/api/documentaciones")
public class DocumentacionController {

    @Autowired
    private DocumentacionService documentacionService;

    @PostMapping
    public ResponseEntity<?> crearDocumentacion(@RequestBody DocumentacionDTO documentacionDTO) {
        try {
            Documentacion documentacion = documentacionService.crearDocumentacion(documentacionDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Documentacion creado exitosamente");
            response.put("DocumentacionId", documentacion.getId());
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
    public ResponseEntity<List<DocumentacionDTO>> obtenerTodosLosDocumentaciones() {
        List<DocumentacionDTO> Documentacion = documentacionService.obtenerDocumentacion();
        return ResponseEntity.ok(Documentacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentacionDTO> obtenerDocumentacion(@PathVariable Integer id) {
        DocumentacionDTO documentacionDTO = documentacionService.obtenerDocumentacionPorId(id);
        return ResponseEntity.ok(documentacionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarDocumentacion(@PathVariable Integer id, @RequestBody DocumentacionDTO documentacionDTO) {
        try {
            documentacionService.actualizarDocumentacion(id, documentacionDTO);
            return ResponseEntity.ok("Documentacion actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el Documentacion: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDocumentacion(@PathVariable Integer id) {
        try {
            documentacionService.eliminarDocumentacion(id);
            return ResponseEntity.ok("Documentacion eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el Documentacion: " + e.getMessage());
        }
    }
}