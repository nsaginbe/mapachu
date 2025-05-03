package org.nurgisa.mapachu.repository;

import org.nurgisa.mapachu.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findByZoneId(Long zoneId);
}
