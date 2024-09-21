package com.proyecto.api.repository;

import com.proyecto.api.model.Alumnos;
import com.proyecto.api.model.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesores, Long> {
}
