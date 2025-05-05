package org.nurgisa.mapachu.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "pokeballs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokeball {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "catch_rate_multiplier", nullable = false)
    private float catchRateMultiplier;

    // Unused, but kept for future use
    @Column(name = "price", nullable = false)
    private Integer price;
}
