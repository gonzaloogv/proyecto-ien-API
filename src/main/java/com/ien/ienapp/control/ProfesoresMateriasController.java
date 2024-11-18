package com.ien.ienapp.control;

import com.ien.ienapp.dto.ProfesoresDTO;
import com.ien.ienapp.entity.ProfesoresMaterias;
import com.ien.ienapp.service.ProfesoresMateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/profesoresmaterias")
public class ProfesoresMateriasController {

    @Autowired
    private ProfesoresMateriasService profesoresMateriasService;

    @GetMapping
    public List<ProfesoresDTO> getAllProfesoresMaterias() {
        return profesoresMateriasService.obtenerProfesoresMaterias();
    }

    @GetMapping("/{idProfesor}/{idMateria}")
    public ResponseEntity<ProfesoresDTO> obtenerProfesoresMateriasPorId(
            @PathVariable Integer idProfesor,
            @PathVariable Integer idMateria) {
        
        Optional<ProfesoresMaterias> profesoresMaterias = profesoresMateriasService.obtenerProfesoresMateriasPorId(idProfesor, idMateria);
        
        return profesoresMaterias
                .map(value -> ResponseEntity.ok(profesoresMateriasService.convertirProfesoresMateriasDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<List<ProfesoresDTO>> obtenerMateriasPorIdProfesor(@PathVariable Integer idProfesor) {
        List<ProfesoresDTO> materias = profesoresMateriasService.obtenerMateriasPorIdProfesor(idProfesor);
        if (materias.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(materias);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProfesoresMaterias(@Valid @RequestBody ProfesoresDTO profesorDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            ProfesoresMaterias newProfesoresMaterias = profesoresMateriasService.crearProfesoresMaterias(profesorDTO);
            return ResponseEntity.ok(profesoresMateriasService.convertirProfesoresMateriasDTO(newProfesoresMaterias));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear ProfesoresMaterias: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idProfesor}/{idMateria}")
    public ResponseEntity<?> deleteProfesoresMaterias(@PathVariable Integer idProfesor, @PathVariable Integer idMateria) {
        try {
            profesoresMateriasService.eliminarProfesoresMaterias(idProfesor, idMateria);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al eliminar ProfesoresMaterias: " + e.getMessage());
        }
    }
}
