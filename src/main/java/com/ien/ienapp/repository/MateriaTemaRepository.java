package com.ien.ienapp.repository;

import com.ien.ienapp.entity.MateriaTema;
import com.ien.ienapp.entity.MateriaTemaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaTemaRepository extends JpaRepository<MateriaTema, MateriaTemaId> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
