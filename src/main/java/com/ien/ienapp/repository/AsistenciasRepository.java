package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Asistencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciasRepository extends JpaRepository<Asistencias, Integer> {
    List<Asistencias> findByComisionDetalle_Id(Integer idComisionDetalle);
}
