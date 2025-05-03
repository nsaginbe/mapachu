package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.UserPokemon;
import org.nurgisa.mapachu.repository.UserPokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final UserPokemonRepository userPokemonRepository;

    public List<UserPokemon> getByUserId(Long userId) {
        return userPokemonRepository.findByUserId(userId);
    }

    public void release(Long upId) {
        userPokemonRepository.deleteById(upId);
    }
}
