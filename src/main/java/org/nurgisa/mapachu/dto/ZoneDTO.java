package org.nurgisa.mapachu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.Zone;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneDTO {
    private Long id;
    private double centerLat;
    private double centerLon;
    private double width;
    private double height;
    private String name;
    private String description;

    public static ZoneDTO fromEntity(Zone z) {
        return ZoneDTO.builder()
                .id(z.getId())
                .centerLat(z.getCenterLat())
                .centerLon(z.getCenterLon())
                .width(z.getWidth())
                .height(z.getHeight())
                .name(z.getName())
                .description(z.getDescription())
                .build();
    }

    public Zone toEntity() {
        Zone z = new Zone();
        z.setCenterLat(centerLat);
        z.setCenterLon(centerLon);
        z.setWidth(width);
        z.setHeight(height);
        z.setName(name);
        z.setDescription(description);
        return z;
    }
}
