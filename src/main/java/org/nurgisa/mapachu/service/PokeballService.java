package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Pokeball;
import org.nurgisa.mapachu.repository.PokeballRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PokeballService {
    private final PokeballRepository pokeballRepository;

    public List<Pokeball> findAll() {
        return pokeballRepository.findAll();
    }

    public Optional<Pokeball> findByType(String type) {
        return pokeballRepository.findByType(type);
    }
}