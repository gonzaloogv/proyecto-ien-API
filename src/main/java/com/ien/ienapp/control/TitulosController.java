package com.ien.ienapp.control;

import com.ien.ienapp.dto.ProfesoresDTO;
import com.ien.ienapp.entity.Titulos;
import com.ien.ienapp.repository.TitulosRepository;
import com.ien.ienapp.service.TitulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/titulos")
public class TitulosController {

    @Autowired
    private TitulosService titulosService;

    @Autowired
    private TitulosRepository titulosRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfesoresDTO> obtenerTodosLosTitulos() {
        return titulosService.obtenerTitulos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Titulos> obtenerTitulosPorId(@PathVariable Integer id) {
        return titulosRepository.findById(id);  // Esto sigue devolviendo un Optional
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Titulos crearTitulo(@RequestBody ProfesoresDTO profesoresDTO) {
        return titulosService.crearTitulo(profesoresDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Titulos actualizarTitulo(@PathVariable Integer id, @RequestBody ProfesoresDTO profesoresDTO) {
        return titulosService.actualizarTitulo(id, profesoresDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTitulo(@PathVariable Integer id) {
        titulosService.eliminarTitulo(id);
    }
}
