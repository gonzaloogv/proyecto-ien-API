package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Materia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByCarreraId(Integer carreraId);
}
