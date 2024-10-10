package com.ien.ienapp.service;

import com.ien.ienapp.entity.CategoriaRrhh;
import com.ien.ienapp.repository.CategoriaRrhhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaRrhhService {

    @Autowired
    private CategoriaRrhhRepository categoriaRrhhRepository;

    public List<CategoriaRrhh> getAllCategoriaRrhh() {
        return categoriaRrhhRepository.findAll();
    }

    public Optional<CategoriaRrhh> getCategoriaRrhhById(Integer id) {
        return categoriaRrhhRepository.findById(id);
    }

    public CategoriaRrhh createCategoriaRrhh(CategoriaRrhh categoriaRrhh) {
        return categoriaRrhhRepository.save(categoriaRrhh);
    }

    public void deleteCategoriaRrhh(Integer id) {
        categoriaRrhhRepository.deleteById(id);
    }
}
