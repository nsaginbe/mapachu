package org.nurgisa.mapachu.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pokemon_catches")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonCatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "spawn_id")
    private HiddenPokemon hiddenPokemon;

    @Column(name = "pokeball_type", nullable = false)
    private String pokeballType;

    @Column(name = "catch_time", nullable = false)
    private LocalDateTime catchTime;

    @Column(name = "success", nullable = false)
    private boolean success;

    @Column(name = "xp_earned", nullable = false)
    private Integer xpEarned;
}
