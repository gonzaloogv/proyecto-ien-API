package com.ien.ienapp.control;

import com.ien.ienapp.dto.ModuloDTO; // Asegúrate de importar el DTO
import com.ien.ienapp.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    // Obtener todos los módulos
    @GetMapping
    public List<ModuloDTO> getAllModulos() {
        return moduloService.obtenerTodosLosModulos();
    }

    // Obtener un módulo por ID
    @GetMapping("/{id}")
    public ResponseEntity<ModuloDTO> getModulo(@PathVariable Integer id) {
        Optional<ModuloDTO> optionalModulo = moduloService.obtenerModuloPorId(id); // Cambio aquí
        if (optionalModulo.isPresent()) {
            ModuloDTO moduloDTO = optionalModulo.get();
            return ResponseEntity.ok(moduloDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // Crear un nuevo módulo
    @PostMapping
    public ResponseEntity<ModuloDTO> createModulo(@RequestBody ModuloDTO moduloDTO) {
        ModuloDTO createdModulo = moduloService.crearModulo(moduloDTO);
        return ResponseEntity.status(201).body(createdModulo); // Código 201 para creación
    }

    // Actualizar un módulo existente
    @PutMapping("/{id}")
    public ResponseEntity<ModuloDTO> updateModulo(@PathVariable Integer id, @RequestBody ModuloDTO moduloDTO) {
        ModuloDTO updatedModulo = moduloService.actualizarModulo(id, moduloDTO);
        return updatedModulo != null ? ResponseEntity.ok(updatedModulo) : ResponseEntity.notFound().build();
    }

    // Eliminar un módulo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModulo(@PathVariable Integer id) {
        if (moduloService.eliminarModulo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
