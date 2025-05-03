package org.nurgisa.mapachu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.PokemonDTO;
import org.nurgisa.mapachu.dto.UpsertPokemonDTO;
import org.nurgisa.mapachu.model.Pokemon;
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
    public List<PokemonDTO> getAllPokemons() {
        return pokemonService.findAll().stream()
                .map(PokemonDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getPokemon(@PathVariable("id") Long id) {
        return pokemonService.findById(id)
                .map(PokemonDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> createPokemon(@Valid @RequestBody UpsertPokemonDTO dto) {
        Pokemon saved = pokemonService.save(dto.toEntity());

        return ResponseEntity.ok(PokemonDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable("id") Long id,
                                                    @Valid @RequestBody UpsertPokemonDTO dto) {

        return pokemonService.update(id, dto.toEntity())
                .map(PokemonDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable("id") Long id) {
        if (!pokemonService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}