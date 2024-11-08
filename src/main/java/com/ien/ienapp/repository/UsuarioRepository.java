package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {
    boolean existsByDeNombreCuenta(String deNombreCuenta);
}
