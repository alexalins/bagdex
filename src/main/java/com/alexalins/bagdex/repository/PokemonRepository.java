package com.alexalins.bagdex.repository;

import com.alexalins.bagdex.domain.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
