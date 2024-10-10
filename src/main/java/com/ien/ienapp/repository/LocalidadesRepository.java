package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Localidades;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocalidadesRepository extends JpaRepository<Localidades, Integer> {
    List<Localidades> findByProvinciasId(Integer provinciaId);
}
