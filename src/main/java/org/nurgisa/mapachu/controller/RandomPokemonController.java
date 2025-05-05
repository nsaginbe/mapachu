package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.PokemonDTO;
import org.nurgisa.mapachu.model.Pokemon;
import org.nurgisa.mapachu.service.PokeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class RandomPokemonController {

    private final PokeApiService pokeApiService;

    @GetMapping("/random")
    public List<PokemonDTO> getRandomPokemons(@RequestParam(value = "count", defaultValue = "1") int count) {
        return pokeApiService.fetchRandomPokemon(count).stream()
                .map(PokemonDTO::fromEntity)
                .toList();
    }

}
