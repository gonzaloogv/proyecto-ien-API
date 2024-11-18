package com.ien.ienapp.control;

import com.ien.ienapp.dto.ProfesoresDTO;
import com.ien.ienapp.entity.ProfesoresTitulos;
import com.ien.ienapp.service.ProfesoresTitulosService;
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
@RequestMapping("/api/profesorestitulos")
public class ProfesoresTitulosController {

    @Autowired
    private ProfesoresTitulosService profesoresTitulosService;

    // Obtener todos los títulos de los profesores
    @GetMapping
    public List<ProfesoresDTO> getAllProfesoresTitulos() {
        return profesoresTitulosService.obtenerProfesoresTitulos();
    }

    // Obtener los títulos de un profesor por su ID
    @GetMapping("/{idProfesor}/{idTitulo}")
    public ResponseEntity<ProfesoresDTO> obtenerProfesoresTitulosPorId(
            @PathVariable Integer idProfesor,
            @PathVariable Integer idTitulo) {

        Optional<ProfesoresTitulos> profesoresTitulos = profesoresTitulosService.obtenerProfesoresTitulosPorId(idProfesor, idTitulo);

        return profesoresTitulos
                .map(value -> ResponseEntity.ok(profesoresTitulosService.convertirProfesoresTitulosDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<List<ProfesoresDTO>> obtenerTitulosPorIdProfesor(@PathVariable Integer idProfesor) {
        List<ProfesoresDTO> titulos = profesoresTitulosService.obtenerTitulosPorIdProfesor(idProfesor);
        if (titulos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(titulos);
        }
    }
    // Crear una nueva relación de título para un profesor
    @PostMapping
    public ResponseEntity<?> crearProfesoresTitulos(@Valid @RequestBody ProfesoresDTO profesorDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            ProfesoresTitulos newProfesoresTitulos = profesoresTitulosService.crearProfesoresTitulos(profesorDTO);
            return ResponseEntity.ok(profesoresTitulosService.convertirProfesoresTitulosDTO(newProfesoresTitulos));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear ProfesoresTitulos: " + e.getMessage());
        }
    }

    // Eliminar la relación de título de un profesor
    @DeleteMapping("/{idProfesor}/{idTitulo}")
    public ResponseEntity<?> deleteProfesoresTitulos(@PathVariable Integer idProfesor, @PathVariable Integer idTitulo) {
        try {
            profesoresTitulosService.eliminarProfesoresTitulos(idProfesor, idTitulo);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al eliminar ProfesoresTitulos: " + e.getMessage());
        }
    }
}
