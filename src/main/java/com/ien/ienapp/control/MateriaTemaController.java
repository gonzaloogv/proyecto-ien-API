package com.ien.ienapp.control;

import com.ien.ienapp.dto.MateriaTemaDTO;
import com.ien.ienapp.dto.TemaDTO;
import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.service.MateriaTemaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/materiatema")
public class MateriaTemaController {

    @Autowired
    private MateriaTemaService materiaTemaService;

    @PostMapping
    public ResponseEntity<String> crearRelacion(@RequestBody MateriaTemaDTO materiaTemaDTO) {
        try {
            materiaTemaService.crearRelacion(materiaTemaDTO.getIdMateria(), materiaTemaDTO.getIdTema(), materiaTemaDTO.getFeRegistro());
            return ResponseEntity.status(HttpStatus.CREATED).body("Relación creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la relación: " + e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<MateriaTema>> getAllRelaciones() {
        try {
            List<MateriaTema> relaciones = materiaTemaService.getAllRelaciones();
            return ResponseEntity.ok(relaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
     @GetMapping("/materia/{idMateria}")
    public ResponseEntity<List<TemaDTO>> getTemasByMateriaId(@PathVariable Integer idMateria) {
        try {
            List<TemaDTO> temas = materiaTemaService.getTemasByMateriaId(idMateria);
            return ResponseEntity.ok(temas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
