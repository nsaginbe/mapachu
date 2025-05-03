package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.dto.BuildingDTO;
import org.nurgisa.mapachu.dto.ZoneDTO;
import org.nurgisa.mapachu.service.BuildingService;
import org.nurgisa.mapachu.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class MapController {

    private final ZoneService zoneService;
    private final BuildingService buildingService;

    @GetMapping
    public List<ZoneDTO> getAllZones() {
        return zoneService.findAll()
                .stream()
                .map(ZoneDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{zoneId}")
    public ResponseEntity<ZoneDTO> getZone(@PathVariable("zoneId") Long zoneId) {
        return zoneService.findById(zoneId)
                .map(ZoneDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{zoneId}/buildings")
    public List<BuildingDTO> getBuildings(@PathVariable("zoneId") Long zoneId) {
        return buildingService.findByZoneId(zoneId)
                .stream()
                .map(BuildingDTO::fromEntity)
                .toList();
    }
}
