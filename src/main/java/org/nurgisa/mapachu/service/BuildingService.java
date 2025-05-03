package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Building;
import org.nurgisa.mapachu.repository.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingService {
    private final BuildingRepository buildingRepository;

    public List<Building> findByZoneId(Long zoneId) {
        return buildingRepository.findByZoneId(zoneId);
    }

    public Optional<Building> findById(Long id) {
        return buildingRepository.findById(id);
    }
}
