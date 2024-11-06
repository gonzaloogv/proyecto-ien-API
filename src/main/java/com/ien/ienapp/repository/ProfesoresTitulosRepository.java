package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.ProfesoresTitulos;
import com.ien.ienapp.entity.ProfesoresTitulosId;

public interface ProfesoresTitulosRepository extends JpaRepository< ProfesoresTitulos, ProfesoresTitulosId > {
    
}
