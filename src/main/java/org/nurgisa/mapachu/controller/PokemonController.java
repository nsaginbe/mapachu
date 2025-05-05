package org.nurgisa.mapachu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.PokemonDTO;
import org.nurgisa.mapachu.exception.InvalidPokemonException;
import org.nurgisa.mapachu.model.Pokemon;
import org.nurgisa.mapachu.service.PokemonService;
import org.nurgisa.mapachu.util.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
                .orElseThrow(
                        () -> new InvalidPokemonException("pokemon not found")
                );
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> createPokemon(@Valid @RequestBody PokemonDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidPokemonException(ErrorResponse.getErrorMessage(bindingResult));
        }

        Pokemon saved = pokemonService.save(dto.toEntity());

        return ResponseEntity.ok(PokemonDTO.fromEntity(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable("id") Long id,
                                                    @Valid @RequestBody PokemonDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidPokemonException(ErrorResponse.getErrorMessage(bindingResult));
        }

        return pokemonService.update(id, dto.toEntity())
                .map(PokemonDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new InvalidPokemonException("pokemon not found")
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable("id") Long id) {
        if (!pokemonService.deleteById(id)) {
            throw new InvalidPokemonException("pokemon not found");
        }

        return ResponseEntity.ok().build();
    }
}