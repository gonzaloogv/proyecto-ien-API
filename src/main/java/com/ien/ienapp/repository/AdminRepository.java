package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.Administrativo;

public interface AdminRepository extends JpaRepository<Administrativo,Integer>{
    
}
