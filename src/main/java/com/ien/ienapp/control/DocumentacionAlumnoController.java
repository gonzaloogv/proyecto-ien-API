package com.ien.ienapp.control;

import com.ien.ienapp.dto.DocumentacionDTO;
import com.ien.ienapp.entity.DocumentacionAlumno;
import com.ien.ienapp.service.DocumentacionAlumnoService;
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
@RequestMapping("/api/documentacion-alumno")
public class DocumentacionAlumnoController {

    @Autowired
    private DocumentacionAlumnoService documentacionAlumnoService;

    @PostMapping
    public ResponseEntity<?> crearDocumentacionAlumno(@RequestBody DocumentacionDTO documentacionDTO) {
        try {
            DocumentacionAlumno documentacionAlumno = documentacionAlumnoService.crearDocumentacionAlumno(documentacionDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "DocumentacionAlumno creado exitosamente");
            response.put("DocumentacionAlumnoId", documentacionAlumno.getId());
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
    public ResponseEntity<List<DocumentacionDTO>> obtenerTodosLosDocumentacionAlumnoes() {
        List<DocumentacionDTO> DocumentacionAlumno = documentacionAlumnoService.obtenerDocumentacion();
        return ResponseEntity.ok(DocumentacionAlumno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentacionDTO> obtenerDocumentacionAlumno(@PathVariable Integer id) {
        DocumentacionDTO documentacionDTO = documentacionAlumnoService.obtenerDocumentacionAlumnoPorId(id);
        return ResponseEntity.ok(documentacionDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarDocumentacionAlumno(@PathVariable Integer id, @RequestBody DocumentacionDTO documentacionAlumnoDTO) {
        try {
            documentacionAlumnoService.actualizarDocumentacionAlumno(id, documentacionAlumnoDTO);
            return ResponseEntity.ok("DocumentacionAlumno actualizado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el DocumentacionAlumno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDocumentacionAlumno(@PathVariable Integer id) {
        try {
            documentacionAlumnoService.eliminarDocumentacion(id);
            return ResponseEntity.ok("Documentacion alumno eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el DocumentacionAlumno: " + e.getMessage());
        }
    }
}