package com.ien.ienapp.repository;

import com.ien.ienapp.entity.RRHH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RRHHRepository extends JpaRepository<RRHH, Integer> {
    boolean existsByNuDni(Integer dni);
}
