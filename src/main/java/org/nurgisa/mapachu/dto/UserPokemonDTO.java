package org.nurgisa.mapachu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.UserPokemon;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPokemonDTO {
    private Long id;
    private Long pokemonId;
    private String nickname;
    private LocalDateTime caughtAt;

    public static UserPokemonDTO fromEntity(UserPokemon up) {
        return UserPokemonDTO.builder()
                .id(up.getId())
                .pokemonId(up.getPokemon().getId())
                .nickname(up.getNickname())
                .caughtAt(up.getCaughtAt())
                .build();
    }
}