package org.nurgisa.mapachu.repository;

import org.nurgisa.mapachu.model.HiddenPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HiddenPokemonRepository extends JpaRepository<HiddenPokemon, Long> {
    List<HiddenPokemon> findByBuildingZoneIdAndSpawnTimeBeforeAndDespawnTimeAfterAndIsCaughtFalse(
            Long zoneId, LocalDateTime before, LocalDateTime after
    );
}
