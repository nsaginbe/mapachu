package org.nurgisa.mapachu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.HiddenPokemon;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HiddenPokemonDTO {
    private Long id;
    private Long pokemonId;
    private Long buildingId;
    private LocalDateTime spawnTime;
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
}