package com.ien.ienapp.control;

import com.ien.ienapp.dto.ErrorResponseDTO;
import com.ien.ienapp.entity.PlanesEstudios;
import com.ien.ienapp.exception.ResourceNotFoundException;
import com.ien.ienapp.service.PlanesEstudiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/planes-estudios")
public class PlanesEstudiosController {

    @Autowired
    private PlanesEstudiosService planesEstudiosService;

    @GetMapping
    public ResponseEntity<List<PlanesEstudios>> getAllPlanesEstudios() {
        List<PlanesEstudios> planesEstudios = planesEstudiosService.getAllPlanesEstudios();
        return ResponseEntity.ok(planesEstudios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanesEstudiosById(@PathVariable Integer id) {
        PlanesEstudios planesEstudios = planesEstudiosService.getPlanesEstudiosById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan estudio no encontrado con id: " + id));
        return ResponseEntity.ok(planesEstudios);
    }

    @PostMapping
    public ResponseEntity<?> crearPlanesEstudios(@RequestBody PlanesEstudios planesEstudios) {
        PlanesEstudios nuevoPlan = planesEstudiosService.createPlanesEstudios(planesEstudios);
        return new ResponseEntity<>(nuevoPlan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlanesEstudios(@PathVariable Integer id) {
        planesEstudiosService.getPlanesEstudiosById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de estudios no encontrado con id: " + id));
        
        planesEstudiosService.deletePlanesEstudios(id);
        
        ErrorResponseDTO response = new ErrorResponseDTO(
                "Plan de estudios eliminado correctamente",
                HttpStatus.OK.value(),
                "ID: " + id
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
