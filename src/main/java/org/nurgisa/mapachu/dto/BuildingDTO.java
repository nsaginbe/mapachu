package org.nurgisa.mapachu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.Building;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingDTO {
    private Long id;
    private Long zoneId;
    private double latitude;
    private double longitude;
    private String name;

    public static BuildingDTO fromEntity(Building b) {
        return BuildingDTO.builder()
                .id(b.getId())
                .zoneId(b.getZone().getId())
                .latitude(b.getLatitude())
                .longitude(b.getLongitude())
                .name(b.getName())
                .build();
    }
}