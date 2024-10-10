package com.ien.ienapp.control;

import com.ien.ienapp.entity.CategoriaRrhh;
import com.ien.ienapp.service.CategoriaRrhhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categoria-rrhh")
public class CategoriaRrhhController {

    @Autowired
    private CategoriaRrhhService categoriaRrhhService;

    @GetMapping
    public List<CategoriaRrhh> getAllCategoriaRrhh() {
        return categoriaRrhhService.getAllCategoriaRrhh();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaRrhh> getCategoriaRrhhById(@PathVariable Integer id) {
        return categoriaRrhhService.getCategoriaRrhhById(id);
    }

    @PostMapping
    public CategoriaRrhh createCategoriaRrhh(@RequestBody CategoriaRrhh categoriaRrhh) {
        return categoriaRrhhService.createCategoriaRrhh(categoriaRrhh);
    }

    @PutMapping("/{id}")
    public CategoriaRrhh updateCategoriaRrhh(@PathVariable Integer id, @RequestBody CategoriaRrhh categoriaRrhh) {
        categoriaRrhh.setId(id);
        return categoriaRrhhService.createCategoriaRrhh(categoriaRrhh);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoriaRrhh(@PathVariable Integer id) {
        categoriaRrhhService.deleteCategoriaRrhh(id);
    }
}
