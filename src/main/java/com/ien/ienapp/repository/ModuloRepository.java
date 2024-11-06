package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ien.ienapp.entity.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
    
}