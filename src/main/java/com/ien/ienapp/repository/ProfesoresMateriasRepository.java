package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.ProfesoresMaterias;
import com.ien.ienapp.entity.ProfesoresMateriasId;

import java.util.List;

public interface ProfesoresMateriasRepository extends JpaRepository<ProfesoresMaterias, ProfesoresMateriasId> {
    // Encuentra todas las materias por idProfesor
    List<ProfesoresMaterias> findById_IdProfesor(Integer idProfesor);
}
