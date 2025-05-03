package org.nurgisa.mapachu.dto;

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
public class UpsertPokemonDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String rarity;

    @NotNull
    @Min(1)
    private Integer strength;

    public Pokemon toEntity() {
        return Pokemon.builder()
                .name(name)
                .type(type)
                .rarity(rarity)
                .strength(strength)
                .build();
    }
}