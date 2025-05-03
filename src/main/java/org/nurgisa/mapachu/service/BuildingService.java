package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Building;
import org.nurgisa.mapachu.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public List<Building> findByZoneId(Long zoneId) {
        return buildingRepository.findByZoneId(zoneId);
    }
}
