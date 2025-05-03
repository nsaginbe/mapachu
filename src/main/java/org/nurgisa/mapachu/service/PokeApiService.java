// src/main/java/org/nurgisa/mapachu/service/PokeApiService.java
package org.nurgisa.mapachu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Random;

@Service
public class PokeApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();
    private static final int MAX_POKEMON_ID = 898;
    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";


    public Map<String, Object> fetchRandomPokemon() {
        int id = random.nextInt(MAX_POKEMON_ID) + 1;
        String url = POKE_API_URL + id;

        return restTemplate.getForObject(url, Map.class);
    }
}
