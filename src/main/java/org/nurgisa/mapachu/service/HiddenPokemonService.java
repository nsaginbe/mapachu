package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.HiddenPokemon;
import org.nurgisa.mapachu.repository.HiddenPokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HiddenPokemonService {
    private final HiddenPokemonRepository hiddenPokemonRepository;

    public List<HiddenPokemon> findActiveInZone(Long zoneId) {
        LocalDateTime now = LocalDateTime.now();

        return hiddenPokemonRepository
                .findByBuildingZoneIdAndSpawnTimeBeforeAndDespawnTimeAfterAndIsCaughtFalse(zoneId, now, now);
    }

    @Transactional
    public HiddenPokemon save(HiddenPokemon hp) {
        return hiddenPokemonRepository.save(hp);
    }

    @Transactional
    public Optional<HiddenPokemon> update(Long id, HiddenPokemon hp) {
        return hiddenPokemonRepository.findById(id)
                .map(existing -> {
                    hp.setId(id);
                    return hiddenPokemonRepository.save(hp);
                });
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (!hiddenPokemonRepository.existsById(id)) {
            return false;
        }

        hiddenPokemonRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Optional<HiddenPokemon> markCaught(Long id) {
        return hiddenPokemonRepository.findById(id)
                .map(hp -> {
                    hp.setCaught(true);
                    return hiddenPokemonRepository.save(hp);
                });
    }
}
