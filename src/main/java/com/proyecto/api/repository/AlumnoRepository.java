package com.proyecto.api.repository;

import com.proyecto.api.model.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumnos, Long> {
}
