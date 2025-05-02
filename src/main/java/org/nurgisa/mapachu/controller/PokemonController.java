package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Pokemon;
import org.nurgisa.mapachu.repository.PokemonRepository;
import org.nurgisa.mapachu.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") Long id) {
        return pokemonService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        Pokemon saved = pokemonService.save(pokemon);

        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable("id") Long id,
                                                 @RequestBody Pokemon pokemon) {
        return pokemonService.update(id, pokemon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable("id") Long id) {
        if (pokemonService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pokemonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
