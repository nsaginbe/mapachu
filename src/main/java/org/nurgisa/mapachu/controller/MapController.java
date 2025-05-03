package org.nurgisa.mapachu.controller;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Building;
import org.nurgisa.mapachu.model.Zone;
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
    public List<Zone> getAllZones() {
        return zoneService.findAll();
    }

    @GetMapping("/{zoneId}")
    public ResponseEntity<Zone> getZone(@PathVariable("zoneId") Long zoneId) {
        return zoneService.findById(zoneId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{zoneId}/buildings")
    public ResponseEntity<List<Building>> getBuildings(@PathVariable("zoneId") Long zoneId) {
        List<Building> list = buildingService.findByZoneId(zoneId);

        return ResponseEntity.ok(list);
    }
}