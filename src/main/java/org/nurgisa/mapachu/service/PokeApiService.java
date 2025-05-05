package org.nurgisa.mapachu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.nurgisa.mapachu.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PokeApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Random random;

    @Value("${pokeapi.max-pokemon}")
    private int maxPokemon;

    @Value("${pokeapi.url}")
    private String pokeApiUrl;

    private final String[] types = {"Fire", "Water", "Grass", "Electric", "Ice", "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon"};
    private final String[] rarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
    private final int[] weights = {50, 25, 15, 7, 3};

    public List<Pokemon> fetchRandomPokemon(int count) {
        List<Pokemon> pokemons = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            int id = random.nextInt(maxPokemon) + 1;
            String url = pokeApiUrl + id;

            String data = restTemplate.getForObject(url, String.class);

            try {
                JsonNode root = objectMapper.readTree(data);
                String name = root.get("forms").get(0).get("name").asText();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);

                Pokemon pokemon = Pokemon.builder()
                        .pokeId(root.get("id").asInt())
                        .name(name)
                        .type(types[random.nextInt(types.length)])
                        .rarity(getRandomRarity())
                        .strength(random.nextInt(100) + 1)
                        .build();

                pokemons.add(pokemon);
            }
            catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return pokemons;
    }

    private String getRandomRarity() {
        int totalWeight = 0;
        for (int weight : weights) totalWeight += weight;

        int rand = random.nextInt(totalWeight);
        int cumulative = 0;

        for (int i = 0; i < weights.length; i++) {
            cumulative += weights[i];
            if (rand < cumulative) {
                return rarities[i];
            }
        }

        return rarities[0];
    }
}
