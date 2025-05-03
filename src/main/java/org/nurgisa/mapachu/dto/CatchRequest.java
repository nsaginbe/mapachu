// src/main/java/org/nurgisa/mapachu/dto/CatchRequest.java
package org.nurgisa.mapachu.dto;

import lombok.Data;

/**
 * DTO для запроса поимки:
 * - spawnId       — ID спавна (hidden_pokemons.id)
 * - pokeballType  — тип покебола (например, "ultra")
 * - userId        — ID пользователя, пытающегося поймать
 */
@Data
public class CatchRequest {
    private Long spawnId;
    private String pokeballType;
    private Long userId;
}
