package com.alexalins.bagdex.repository;

import com.alexalins.bagdex.domain.model.Bolsa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BolsaRepository extends JpaRepository<Bolsa, Long> {
    List<Bolsa> findByTreinadorId(Long id);
}
