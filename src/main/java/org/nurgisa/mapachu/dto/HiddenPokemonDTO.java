package org.nurgisa.mapachu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.Building;
import org.nurgisa.mapachu.model.HiddenPokemon;
import org.nurgisa.mapachu.model.Pokemon;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HiddenPokemonDTO {
    private Long id;

    @NotNull
    private Long pokemonId;

    @NotNull
    private Long buildingId;

    @NotNull
    private LocalDateTime spawnTime;

    @NotNull
    private LocalDateTime despawnTime;

    private boolean isCaught;

    public static HiddenPokemonDTO fromEntity(HiddenPokemon hp) {
        return HiddenPokemonDTO.builder()
                .id(hp.getId())
                .pokemonId(hp.getPokemon().getId())
                .buildingId(hp.getBuilding().getId())
                .spawnTime(hp.getSpawnTime())
                .despawnTime(hp.getDespawnTime())
                .isCaught(hp.isCaught())
                .build();
    }

    public HiddenPokemon toEntity(Pokemon pokemon, Building building) {
        return HiddenPokemon.builder()
                .pokemon(pokemon)
                .building(building)
                .spawnTime(this.spawnTime)
                .despawnTime(this.despawnTime)
                .isCaught(false)
                .build();
    }
}