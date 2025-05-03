// src/main/java/org/nurgisa/mapachu/dto/CatchResult.java
package org.nurgisa.mapachu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO для ответа на попытку поимки:
 * - success   — удалось ли поймать
 * - xpGained  — сколько XP заработал игрок
 * - message   — текстовое сообщение (например, "Поймано!" или "Упс, убежал...")
 */
@Data
@AllArgsConstructor
public class CatchResult {
    private boolean success;
    private int xpGained;
    private String message;
}
