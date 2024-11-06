package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.Inscripciones;
import com.ien.ienapp.entity.InscripcionesId;

public interface InscripcionesRepository extends JpaRepository <Inscripciones, InscripcionesId> {
    
}
