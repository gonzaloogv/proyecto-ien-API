package com.ien.ienapp.control;

import com.ien.ienapp.dto.OperacionDTO;
import com.ien.ienapp.service.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/operaciones")
public class OperacionController {

    @Autowired
    private OperacionService operacionService;

    @GetMapping
    public List<OperacionDTO> getAllOperaciones() {
        return operacionService.obtenerTodasLasOperaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperacionDTO> getOperacion(@PathVariable Integer id) {
        Optional<OperacionDTO> optionalOperacion = operacionService.obtenerOperacionPorId(id);
        if (optionalOperacion.isPresent()) {
            OperacionDTO operacionDTO = optionalOperacion.get();
            return ResponseEntity.ok(operacionDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OperacionDTO> createOperacion(@RequestBody OperacionDTO operacionDTO) {
        OperacionDTO createdOperacion = operacionService.crearOperacion(operacionDTO);
        return ResponseEntity.status(201).body(createdOperacion); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperacionDTO> updateOperacion(@PathVariable Integer id, @RequestBody OperacionDTO operacionDTO) {
        Optional<OperacionDTO> updatedOperacion = operacionService.actualizarOperacion(id, operacionDTO);
        return updatedOperacion
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperacion(@PathVariable Integer id) {
        if (operacionService.eliminarOperacion(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
