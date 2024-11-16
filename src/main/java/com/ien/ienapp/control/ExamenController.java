package com.ien.ienapp.control;

import com.ien.ienapp.dto.ExamenDTO;
import com.ien.ienapp.service.ExamenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @PostMapping
    public ResponseEntity<ExamenDTO> crearExamen(@RequestBody ExamenDTO examenDTO) {
        ExamenDTO nuevoExamen = examenService.crearExamen(examenDTO);
        return new ResponseEntity<>(nuevoExamen, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExamenDTO>> obtenerExamenes() {
        List<ExamenDTO> examen = examenService.obtenerExamen();
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenDTO> obtenerExamenPorId(@PathVariable Integer id) {
        ExamenDTO examen = examenService.obtenerExamenPorId(id);
        return new ResponseEntity<>(examen, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamenDTO> actualizarExamen(@PathVariable Integer id, @RequestBody ExamenDTO examenDTO) {
        ExamenDTO examenActualizado = examenService.actualizarExamen(id, examenDTO);
        return new ResponseEntity<>(examenActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarExamen(@PathVariable Integer id) {
        examenService.eliminarExamen(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
