package org.nurgisa.mapachu.repository;

import org.nurgisa.mapachu.model.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
    List<UserPokemon> findByUserId(Long userId);
}
