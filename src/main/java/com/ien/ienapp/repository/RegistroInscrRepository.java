package com.ien.ienapp.repository;

import com.ien.ienapp.entity.RegistroInscr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroInscrRepository extends JpaRepository<RegistroInscr, Long> {
}
