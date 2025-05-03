// src/main/java/org/nurgisa/mapachu/controller/SpawnController.java
package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.HiddenPokemon;
import org.nurgisa.mapachu.service.HiddenPokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spawns")
@RequiredArgsConstructor
public class SpawnController {

    private final HiddenPokemonService hiddenPokemonService;

    @GetMapping
    public List<HiddenPokemon> getActiveSpawns(@RequestParam Long zoneId) {
        return hiddenPokemonService.findActiveInZone(zoneId);
    }

    @PostMapping
    public ResponseEntity<HiddenPokemon> createSpawn(@RequestBody HiddenPokemon hp) {
        HiddenPokemon saved = hiddenPokemonService.save(hp);

        return ResponseEntity.ok(saved);
    }

    @PatchMapping("/{spawnId}/caught")
    public ResponseEntity<HiddenPokemon> catchSpawn(@PathVariable("spawnId") Long spawnId) {
        return hiddenPokemonService.markCaught(spawnId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
