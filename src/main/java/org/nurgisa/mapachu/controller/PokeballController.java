package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.PokeballDTO;
import org.nurgisa.mapachu.service.PokeballService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokeballs")
@RequiredArgsConstructor
public class PokeballController {

    private final PokeballService pokeballService;

    @GetMapping
    public List<PokeballDTO> getAllPokeballs() {
        return pokeballService.findAll().stream()
                .map(PokeballDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{type}")
    public ResponseEntity<PokeballDTO> getPokeball(@PathVariable("type") String type) {
        return pokeballService.findByType(type)
                .map(PokeballDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}