package org.nurgisa.mapachu.repository;

import org.nurgisa.mapachu.model.Pokeball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokeballRepository extends JpaRepository<Pokeball, Long> {
    Optional<Pokeball> findByType(String type);
}
