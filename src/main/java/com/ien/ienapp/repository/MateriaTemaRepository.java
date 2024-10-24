package com.ien.ienapp.repository;

import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.entity.MateriaTemaId; // Asegúrate de importar la clase correcta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaTemaRepository extends JpaRepository<MateriaTema, MateriaTemaId> {
}
