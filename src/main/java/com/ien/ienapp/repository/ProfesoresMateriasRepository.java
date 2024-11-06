package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.ProfesoresMaterias;
import com.ien.ienapp.entity.ProfesoresMateriasId;

public interface ProfesoresMateriasRepository extends JpaRepository< ProfesoresMaterias, ProfesoresMateriasId > {
    
}
