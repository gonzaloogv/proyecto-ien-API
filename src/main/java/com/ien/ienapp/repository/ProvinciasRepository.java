package com.ien.ienapp.repository;

import com.ien.ienapp.entity.Provincias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciasRepository extends JpaRepository<Provincias, Integer> {
}
