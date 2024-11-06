package com.ien.ienapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ien.ienapp.entity.Documentacion;

public interface DocumentacionRepository extends JpaRepository <Documentacion, Integer> {
}
