package com.ien.ienapp.control;

import com.ien.ienapp.dto.InscripcionesDTO;
import com.ien.ienapp.service.InscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionesController {

    @Autowired
    private InscripcionesService inscripcionesService;

    @PostMapping
    public ResponseEntity<InscripcionesDTO> crearInscripcion(@RequestBody InscripcionesDTO inscripcionesDTO) {
        InscripcionesDTO createdInscripcion = inscripcionesService.crearInscripcion(inscripcionesDTO);
        return ResponseEntity.ok(createdInscripcion);
    }

    @GetMapping
    public ResponseEntity<List<InscripcionesDTO>> obtenerInscripciones() {
        List<InscripcionesDTO> inscripciones = inscripcionesService.obtenerInscripciones();
        return ResponseEntity.ok(inscripciones);
    }
}
