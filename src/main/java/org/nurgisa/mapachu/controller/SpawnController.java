package org.nurgisa.mapachu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.HiddenPokemonDTO;
import org.nurgisa.mapachu.model.Building;
import org.nurgisa.mapachu.model.HiddenPokemon;
import org.nurgisa.mapachu.model.Pokemon;
import org.nurgisa.mapachu.service.BuildingService;
import org.nurgisa.mapachu.service.HiddenPokemonService;
import org.nurgisa.mapachu.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spawns")
@RequiredArgsConstructor
public class SpawnController {

    private final HiddenPokemonService hiddenPokemonService;
    private final PokemonService pokemonService;
    private final BuildingService buildingService;

    @GetMapping
    public List<HiddenPokemonDTO> getActiveSpawns(@RequestParam("zoneId") Long zoneId) {
        return hiddenPokemonService.findActiveInZone(zoneId).stream()
                .map(HiddenPokemonDTO::fromEntity)
                .toList();
    }

    @PostMapping
    public ResponseEntity<HiddenPokemonDTO> createSpawn(@Valid @RequestBody HiddenPokemonDTO dto) {
        Optional<Pokemon> pokemon = pokemonService.findById(dto.getPokemonId());
        Optional<Building> building = buildingService.findById(dto.getBuildingId());

        if (pokemon.isEmpty() || building.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        HiddenPokemon saved = hiddenPokemonService.save(dto.toEntity(
                pokemon.get(),
                building.get()
        ));

        return ResponseEntity.ok(HiddenPokemonDTO.fromEntity(saved));
    }

    @PatchMapping("/{id}/caught")
    public ResponseEntity<HiddenPokemonDTO> catchSpawn(@PathVariable("id") Long id) {
        return hiddenPokemonService.markCaught(id)
                .map(HiddenPokemonDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
