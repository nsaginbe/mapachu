package org.nurgisa.mapachu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.Pokeball;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokeballDTO {
    private Long id;
    private String type;
    private float catchRateMultiplier;
    private Integer price;

    public static PokeballDTO fromEntity(Pokeball p) {
        return PokeballDTO.builder()
                .id(p.getId())
                .type(p.getType())
                .catchRateMultiplier(p.getCatchRateMultiplier())
                .price(p.getPrice())
                .build();
    }
}