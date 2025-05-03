package org.nurgisa.mapachu.repository;

import org.nurgisa.mapachu.model.PokemonCatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonCatchRepository extends JpaRepository<PokemonCatch, Long> {
    List<PokemonCatch> findByUserId(Long userId);
}
