// src/main/java/org/nurgisa/mapachu/controller/PokeballController.java
package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Pokeball;
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
    public List<Pokeball> getAllPokeballs() {
        return pokeballService.findAll();
    }

    @GetMapping("/{type}")
    public ResponseEntity<Pokeball> getPokeball(@PathVariable("type") String type) {
        return pokeballService.findByType(type)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
