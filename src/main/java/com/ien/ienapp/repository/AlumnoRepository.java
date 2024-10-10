
package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    // Métodos personalizados si es necesario
}