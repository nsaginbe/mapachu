package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Pokemon;
import org.nurgisa.mapachu.repository.PokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PokemonService {

    private final PokemonRepository pokemonRepository;


    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> findById(Long id) {
        return pokemonRepository.findById(id);
    }

    @Transactional
    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Transactional
    public Optional<Pokemon> update(Long id, Pokemon pokemon) {
        return pokemonRepository.findById(id)
                .map(existing -> {
                    pokemon.setId(id);
                    return pokemonRepository.save(pokemon);
                });
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (!pokemonRepository.existsById(id)) {
            return false;
        }

        pokemonRepository.deleteById(id);
        return true;
    }
}
