package com.alexalins.bagdex.repository;

import com.alexalins.bagdex.domain.model.Bolsa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolsaRepository extends JpaRepository<Bolsa, Long> {
    Bolsa findByTreinadorId(Long id);

}
