package com.ien.ienapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {
    List<Examen> findByMateria_Id(Integer idMateria);
}
