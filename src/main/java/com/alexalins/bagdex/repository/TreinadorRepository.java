package com.alexalins.bagdex.repository;

import com.alexalins.bagdex.domain.model.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinadorRepository extends JpaRepository<Treinador, Long> {
    Treinador findByEmail(String email);
}

