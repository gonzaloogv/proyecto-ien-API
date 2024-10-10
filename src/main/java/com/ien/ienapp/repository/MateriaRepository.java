package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
