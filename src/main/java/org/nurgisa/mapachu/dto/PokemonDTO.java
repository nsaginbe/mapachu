package org.nurgisa.mapachu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.Pokemon;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonDTO {
    private Long id;
    private String name;
    private String type;
    private String rarity;
    private Integer strength;

    public static PokemonDTO fromEntity(Pokemon p) {
        return PokemonDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .type(p.getType())
                .rarity(p.getRarity())
                .strength(p.getStrength())
                .build();
    }
}
