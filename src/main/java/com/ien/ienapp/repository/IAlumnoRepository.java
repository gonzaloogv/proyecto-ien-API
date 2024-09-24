
package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlumnoRepository extends JpaRepository <Alumno, Long> {
    
    
    
}
