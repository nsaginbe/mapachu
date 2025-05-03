// src/main/java/org/nurgisa/mapachu/controller/CatchController.java
package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.CatchRequest;
import org.nurgisa.mapachu.dto.CatchResult;
import org.nurgisa.mapachu.service.CatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catch")
@RequiredArgsConstructor
public class CatchController {

    private final CatchService catchService;

    @PostMapping
    public ResponseEntity<CatchResult> catchPokemon(@RequestBody CatchRequest req) {
        CatchResult result = catchService.attemptCatch(req);
        return ResponseEntity.ok(result);
    }
}
