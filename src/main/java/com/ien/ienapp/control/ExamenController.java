package com.ien.ienapp.control;

import com.ien.ienapp.dto.ExamenDTO;
import com.ien.ienapp.entity.Examen;
import com.ien.ienapp.service.ExamenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/materia/{idMateria}") 
    public ResponseEntity<List<ExamenDTO>> getExamenesByMateriaId(@PathVariable Integer idMateria) { 
        try { 
            List<Examen> examenes = examenService.getExamenesByMateriaId(idMateria); 
            List<ExamenDTO> examenesDTO = examenes.stream() 
            .map(examen -> new ExamenDTO(
                examen.getId(), 
                examen.getMateria().getId(), 
                examen.getAlumno().getId(), 
                examen.getProfesor().getId(), 
                examen.getFeExamen(),
                examen.getDeCondicion(),
                examen.getNuNota(),
                examen.getTiExamen(),
                examen.getHrExamen(),
                examen.getSnAusente()
                )) 
            .collect(Collectors.toList()); return ResponseEntity.ok(examenesDTO); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
        } 
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
