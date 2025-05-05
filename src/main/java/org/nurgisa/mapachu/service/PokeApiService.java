package org.nurgisa.mapachu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PokeApiService {

    private final RestTemplate restTemplate;
    private final Random random;

    @Value("${pokeapi.max-pokemon}")

    private int maxPokemon;

    @Value("${pokeapi.url}")
    private String pokeApiUrl;


    public Map<String, Object> fetchRandomPokemon() {
        int id = random.nextInt(maxPokemon) + 1;
        String url = pokeApiUrl + id;

        return restTemplate.getForObject(url, Map.class);
    }
}
