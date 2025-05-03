package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.CatchRequest;
import org.nurgisa.mapachu.dto.CatchResult;
import org.nurgisa.mapachu.model.HiddenPokemon;
import org.nurgisa.mapachu.model.PokemonCatch;
import org.nurgisa.mapachu.model.User;
import org.nurgisa.mapachu.model.UserPokemon;
import org.nurgisa.mapachu.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatchService {

    private final HiddenPokemonRepository hiddenPokemonRepository;
    private final PokeballRepository pokeballRepository;
    private final PokemonCatchRepository pokemonCatchRepository;
    private final UserPokemonRepository userPokemonRepository;
    private final UserRepository userRepository;

    @Value("${game.base-chance}")
    private double baseChance;

    @Value("${game.xp-per-catch}")
    private int xpPerCatch;

    @Transactional
    public CatchResult attemptCatch(CatchRequest request) {
        HiddenPokemon spawn = hiddenPokemonRepository.findById(request.getSpawnId())
                .orElseThrow(() -> new RuntimeException("Spawn not found"));

        LocalDateTime now = LocalDateTime.now();
        if (spawn.isCaught()
                || now.isBefore(spawn.getSpawnTime())
                || now.isAfter(spawn.getDespawnTime())) {
            return new CatchResult(false, 0, "Nothing to catch");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        double multiplier = pokeballRepository.findByType(request.getPokeballType())
                .map(p -> (double) p.getCatchRateMultiplier())
                .orElse(1.0);

        boolean success = Math.random() < baseChance * multiplier;
        int xp = success ? xpPerCatch : 0;

        // record the catch attempt
        PokemonCatch catchRecord = PokemonCatch.builder()
                .user(user)
                .hiddenPokemon(spawn)
                .pokeballType(request.getPokeballType())
                .catchTime(now)
                .success(success)
                .xpEarned(xp)
                .build();
        catchRecord = pokemonCatchRepository.save(catchRecord);

        if (success) {
            // mark spawn as caught
            spawn.setCaught(true);
            hiddenPokemonRepository.save(spawn);

            // add to user collection
            UserPokemon up = UserPokemon.builder()
                    .user(user)
                    .pokemon(spawn.getPokemon())
                    .catchRecord(catchRecord)
                    .caughtAt(now)
                    .build();
            userPokemonRepository.save(up);
        }

        return new CatchResult(
                success,
                xp,
                success ? "Success! Pokemon caught." : "Oh no! The Pokemon escaped."
        );
    }
}
