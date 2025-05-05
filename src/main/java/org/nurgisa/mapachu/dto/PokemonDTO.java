package org.nurgisa.mapachu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.Pokemon;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String rarity;

    @NotNull
    @Min(1)
    private Integer strength;

    private Integer pokeId;

    public static PokemonDTO fromEntity(Pokemon p) {
        return PokemonDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .type(p.getType())
                .rarity(p.getRarity())
                .strength(p.getStrength())
                .pokeId(p.getPokeId())
                .build();
    }

    public Pokemon toEntity() {
        return Pokemon.builder()
                .name(this.name)
                .type(this.type)
                .rarity(this.rarity)
                .strength(this.strength)
                .build();
    }
}
