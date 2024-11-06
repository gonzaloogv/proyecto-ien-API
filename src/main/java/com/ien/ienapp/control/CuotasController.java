package com.ien.ienapp.control;

import com.ien.ienapp.dto.CuotasDTO;
import com.ien.ienapp.service.CuotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuotas")
public class CuotasController {

    @Autowired
    private CuotasService cuotasService;

    @PostMapping
    public ResponseEntity<CuotasDTO> crearCuota(@RequestBody CuotasDTO cuotasDTO) {
        CuotasDTO nuevaCuota = cuotasService.crearCuota(cuotasDTO);
        return new ResponseEntity<>(nuevaCuota, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CuotasDTO>> obtenerCuotas() {
        List<CuotasDTO> cuotas = cuotasService.obtenerCuotas();
        return new ResponseEntity<>(cuotas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuotasDTO> obtenerCuotaPorId(@PathVariable Integer id) {
        CuotasDTO cuota = cuotasService.obtenerCuotaPorId(id);
        return new ResponseEntity<>(cuota, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuotasDTO> actualizarCuota(@PathVariable Integer id, @RequestBody CuotasDTO cuotasDTO) {
        CuotasDTO cuotaActualizada = cuotasService.actualizarCuota(id, cuotasDTO);
        return new ResponseEntity<>(cuotaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuota(@PathVariable Integer id) {
        cuotasService.eliminarCuota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
