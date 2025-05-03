package org.nurgisa.mapachu.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_pokemons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @OneToOne
    @JoinColumn(name = "catch_id")
    private PokemonCatch catchRecord;

    @Column(name = "caught_at", nullable = false)
    private LocalDateTime caughtAt;

    @Column(name = "nickname")
    private String nickname;
}
