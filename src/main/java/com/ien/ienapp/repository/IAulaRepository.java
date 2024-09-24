package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAulaRepository extends JpaRepository <Aula, Long> {
    
    
    
}
