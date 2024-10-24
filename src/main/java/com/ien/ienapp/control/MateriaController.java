package com.ien.ienapp.control;

import com.ien.ienapp.dto.MateriaDTO; // Asegúrate de importar el DTO
import com.ien.ienapp.entity.Materia;
import com.ien.ienapp.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/materias")
public class MateriaController {
    
    @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias
    @GetMapping
    public List<MateriaDTO> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getMateria(@PathVariable Integer id) {
        Optional<Materia> optionalMateria = materiaService.obtenerMateriaPorId(id);
        if (optionalMateria.isPresent()) {
            Materia materia = optionalMateria.get();
            MateriaDTO materiaDTO = new MateriaDTO();
            materiaDTO.setId(materia.getId());
            materiaDTO.setDeNombre(materia.getDeNombre());
            materiaDTO.setTaAsistenciaObligatoria(materia.getTaAsistenciaObligatoria());
            // Establecer otros campos según sea necesario
            return ResponseEntity.ok(materiaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@RequestBody MateriaDTO materiaDTO) {
        MateriaDTO createdMateria = materiaService.crearMateria(materiaDTO);
        return ResponseEntity.status(201).body(createdMateria); // Código 201 para creación
    }

    // Actualizar una materia existente
    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> updateMateria(@PathVariable Integer id, @RequestBody MateriaDTO materiaDTO) {
        MateriaDTO updatedMateria = materiaService.actualizarMateria(id, materiaDTO);
        return updatedMateria != null ? ResponseEntity.ok(updatedMateria) : ResponseEntity.notFound().build();
    }

    // Eliminar una materia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Integer id) {
        if (materiaService.eliminarMateria(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
