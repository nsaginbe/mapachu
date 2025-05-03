package org.nurgisa.mapachu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PokeApiService {

    private final RestTemplate rest = new RestTemplate();
    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";

    public Map<String, Object> fetchRandomPokemon() {
        int randomId = 1 + (int)(Math.random() * 898);
        String url = POKE_API_URL + randomId;

        return rest.getForObject(url, Map.class);
    }
}
