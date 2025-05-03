package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.UserPokemon;
import org.nurgisa.mapachu.repository.UserPokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionService {
    private final UserPokemonRepository userPokemonRepository;

    public List<UserPokemon> getByUserId(Long userId) {
        return userPokemonRepository.findByUserId(userId);
    }

    @Transactional
    public void delete(Long upId) {
        userPokemonRepository.deleteById(upId);
    }
}
