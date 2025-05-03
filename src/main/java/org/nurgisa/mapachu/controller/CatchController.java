package org.nurgisa.mapachu.controller;

import jakarta.validation.Valid;
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
    public ResponseEntity<CatchResult> attemptCatch(@Valid @RequestBody CatchRequest request) {
        CatchResult result = catchService.attemptCatch(request);

        return ResponseEntity.ok(result);
    }
}