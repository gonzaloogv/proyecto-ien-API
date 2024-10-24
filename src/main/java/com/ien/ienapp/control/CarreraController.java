package com.ien.ienapp.control;

import com.ien.ienapp.dto.CarreraDTO;
import com.ien.ienapp.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping
    public ResponseEntity<CarreraDTO> crearCarrera(@RequestBody CarreraDTO carreraDTO) {
        CarreraDTO nuevaCarrera = carreraService.crearCarrera(carreraDTO);
        return ResponseEntity.ok(nuevaCarrera);
    }

    @GetMapping
    public List<CarreraDTO> getAllCarreras() {
        return carreraService.getAllCarreras(); // Aquí devuelves List<CarreraDTO>
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> obtenerCarreraPorId(@PathVariable Integer id) {
        return carreraService.obtenerCarreraPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> actualizarCarrera(@PathVariable Integer id, @RequestBody CarreraDTO carreraDTO) {
        CarreraDTO updatedCarrera = carreraService.actualizarCarrera(id, carreraDTO);
        return ResponseEntity.ok(updatedCarrera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrera(@PathVariable Integer id) {
        carreraService.eliminarCarrera(id);
        return ResponseEntity.noContent().build();
    }
}
