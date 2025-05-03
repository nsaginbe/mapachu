package org.nurgisa.mapachu.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "hidden_pokemons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HiddenPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @OneToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false, unique = true)
    private Building building;

    @Column(name = "spawn_time", nullable = false)
    private LocalDateTime spawnTime;

    @Column(name = "despawn_time", nullable = false)
    private LocalDateTime despawnTime;

    @Column(name = "is_caught", nullable = false)
    private boolean isCaught;
}
