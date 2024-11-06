package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.Operacion;

public interface OperacionRepository extends JpaRepository<Operacion, Integer> {
    
}
