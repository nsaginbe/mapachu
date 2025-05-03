package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.UserPokemonDTO;
import org.nurgisa.mapachu.service.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public List<UserPokemonDTO> getCollection(@RequestParam("userId") Long userId) {
        return collectionService.getByUserId(userId).stream()
                .map(UserPokemonDTO::fromEntity)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> releasePokemon(@PathVariable("id") Long id) {
        collectionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}