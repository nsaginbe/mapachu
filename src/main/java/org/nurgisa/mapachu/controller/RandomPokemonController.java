// src/main/java/org/nurgisa/mapachu/controller/RandomPokemonController.java
package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.service.PokeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class RandomPokemonController {

    private final PokeApiService pokeApiService;

    @GetMapping("/random")
    public ResponseEntity<Map<String, Object>> getRandomPokemon() {
        Map<String, Object> data = pokeApiService.fetchRandomPokemon();

        if (data == null) {
            return ResponseEntity.status(502).build();
        }

        return ResponseEntity.ok(data);
    }
}
