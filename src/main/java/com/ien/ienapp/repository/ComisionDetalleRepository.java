package com.ien.ienapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.ComisionDetalle;

public interface ComisionDetalleRepository extends JpaRepository<ComisionDetalle, Integer> {
    List<ComisionDetalle> findByMateriaId(Integer idMateria);
}
