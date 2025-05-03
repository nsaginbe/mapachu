package org.nurgisa.mapachu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import org.nurgisa.mapachu.model.HiddenPokemon;
import org.nurgisa.mapachu.model.Pokemon;
import org.nurgisa.mapachu.model.Building;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateHiddenPokemonDTO {
    @NotNull
    private Long pokemonId;
    @NotNull
    private Long buildingId;
    @NotNull
    private LocalDateTime spawnTime;
    @NotNull
    private LocalDateTime despawnTime;

    public HiddenPokemon toEntity(Pokemon pokemon, Building building) {
        HiddenPokemon h = new HiddenPokemon();
        h.setPokemon(pokemon);
        h.setBuilding(building);
        h.setSpawnTime(spawnTime);
        h.setDespawnTime(despawnTime);
        h.setCaught(false);
        return h;
    }
}