import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const apiService = {
    // Get all zones
    getZones: async () => {
        try {
            const response = await axios.get(`${API_BASE_URL}/zones`);
            console.log('[API] Zones fetched:', response.data);

            return response.data;
        }
        catch (error) {
            console.error('Error fetching zones:', error);
            throw error;
        }
    },
  
    // Get buildings in a zone
    getBuildingsByZone: async (zoneId) => {
        try {
            const response = await axios.get(`${API_BASE_URL}/zones/${zoneId}/buildings`);
            console.log('[API] Buildings fetched:', response.data);

            return response.data;
        }
        catch (error) {
            console.error('Error fetching buildings:', error);
            throw error;
        }
    },
  
    // Get random Pokémon
    getRandomPokemons: async (count = 1) => {
        try {
            const response = await axios.get(`${API_BASE_URL}/pokemon/random`, {
                params: { count },
            });
            console.log('[API] Random Pokemon fetched:', response.data);

            return response.data;
        }
        catch (error) {
            console.error('Error fetching random Pokémon:', error);
            throw error;
        }
    },
};

export default apiService;