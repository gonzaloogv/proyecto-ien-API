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

    @GetMapping("/{id}")
    public ResponseEntity<ProfesoresDTO> obtenerProfesoresMateriasPorId(@PathVariable Integer id) {
        Optional<ProfesoresMaterias> profesoresMaterias = profesoresMateriasService.obtenerProfesoresMateriasPorId(id);
        return profesoresMaterias.map(value -> ResponseEntity.ok(profesoresMateriasService.convertirProfesoresMateriasDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfesoresMaterias(@PathVariable Integer id) {
        try {
            profesoresMateriasService.eliminarProfesoresMaterias(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al eliminar ProfesoresMaterias: " + e.getMessage());
        }
    }
}
