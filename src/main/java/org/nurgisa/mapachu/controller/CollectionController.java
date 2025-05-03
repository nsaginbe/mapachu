// src/main/java/org/nurgisa/mapachu/controller/CollectionController.java
package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.UserPokemon;
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
    public List<UserPokemon> getCollection(@RequestParam Long userId) {
        return collectionService.getByUserId(userId);
    }

    @DeleteMapping("/{upId}")
    public ResponseEntity<Void> release(@PathVariable("upId") Long upId) {
        collectionService.release(upId);

        return ResponseEntity.noContent().build();
    }
}
