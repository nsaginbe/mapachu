import React, { useState, useRef, useEffect } from 'react';
import { MapContainer, TileLayer, Circle, useMap } from 'react-leaflet';
import { useNavigate } from 'react-router-dom';
import 'leaflet/dist/leaflet.css';
import './App.css';
import apiService from './services/api';
import { getBuildingName } from './utils/openstreet';
import { askOpenRouterAboutPlace } from './services/openrouter';


// This component handles map restrictions around a dynamic center and radius
function MapBoundaryController({ center = { lat: 43.238598, lng: 76.945377 }, radiusMeters = 1000 }) {
  const map = useMap();

  useEffect(() => {
    const minZoom = 16;
    const maxZoom = 18;
    map.setMinZoom(minZoom);
    map.setMaxZoom(maxZoom);

    const latDelta = radiusMeters / 111320;
    const lngDelta = radiusMeters / (40075000 * Math.cos(center.lat * Math.PI / 180) / 360);
    const southWest = [center.lat - latDelta, center.lng - lngDelta];
    const northEast = [center.lat + latDelta, center.lng + lngDelta];

    map.setMaxBounds([southWest, northEast]);
    map.on('drag', () => map.panInsideBounds([southWest, northEast], { animate: false }));

    return () => {
      map.off('drag');
    };
  }, [map, center, radiusMeters]);

  return null;
}

function App() {
  const [gameStarted, setGameStarted] = useState(false);
  const [timeLeft, setTimeLeft] = useState(60);
  const [inventory, setInventory] = useState({ normal: 5, great: 3, ultra: 1 });
  const [score, setScore] = useState(0);
  const [buildings, setBuildings] = useState([]);
  const [spawns, setSpawns] = useState([]);
  const [selectedSpawn, setSelectedSpawn] = useState(null);
  const navigate = useNavigate();
  const timerRef = useRef(null);
  const [buildingName, setBuildingName] = useState('');
  const [buildingFact, setBuildingFact] = useState('');



  const loadSpawns = async () => {
    try {
      const blds = await apiService.getBuildingsByZone(1);
      setBuildings(blds);

      const pokes = await apiService.getRandomPokemons(blds.length);
      const newSpawns = blds.map((b, i) => ({
        position: [b.latitude, b.longitude],
        pokemon: {
          pokeId: pokes[i].pokeId,
          name: pokes[i].name,
          type: pokes[i].type,
          rarity: pokes[i].rarity,
          strength: pokes[i].strength,
          photoUrl: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokes[i].pokeId}.png`
        }
      }));
      setSpawns(newSpawns);
    } catch (error) {
      console.error('Error loading spawns:', error);
    }
  };

  const startGame = () => {
    setGameStarted(true);
    setTimeLeft(60);
    setScore(0);
    setInventory({ normal: 5, great: 3, ultra: 1 });
    setSelectedSpawn(null);
    loadSpawns();

    if (timerRef.current) clearInterval(timerRef.current);

    timerRef.current = setInterval(() => {
      setTimeLeft(prev => {
        if (prev <= 1) {
          clearInterval(timerRef.current);
          endGame();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
  };

  const endGame = () => {
    setGameStarted(false);
    alert(`Game Over! Your final score: ${score} points`);
  };

  const handleCircleClick = async (spawn) => {
    setSelectedSpawn(spawn);
    const [lat, lon] = spawn.position;
    const name = await getBuildingName(lat, lon);
    setBuildingName(name);
  
    const fact = await askOpenRouterAboutPlace(name);
    setBuildingFact(fact);
  };
  
  

  const handlePokeballUse = (type) => {
    if (inventory[type] <= 0) {
      alert(`You don't have any ${type} Pokéballs left!`);
      return;
    }

    setInventory(prev => ({ ...prev, [type]: prev[type] - 1 }));

    const success = Math.random() > 0.5;

    if (success) {
      setScore(prev => prev + calculatePoints(selectedSpawn.pokemon.rarity));
      alert(`You caught ${selectedSpawn.pokemon.name}!`);
    
      setSpawns(prev => prev.filter(spawn => spawn !== selectedSpawn));
    }    
    else {
      alert('The Pokemon escaped!');
    }

    setSelectedSpawn(null);
  };

  const calculatePoints = (rarity) => {
    switch (rarity) {
      case 'Common': return 5;
      case 'Uncommon': return 10;
      case 'Rare': return 20;
      case 'Epic': return 50;
      case 'Legendary': return 100;
      default: return 5;
    }
  };

  useEffect(() => {
    startGame();
    return () => {
      if (timerRef.current) clearInterval(timerRef.current);
    };
  }, []);

  const almatyCenter = { lat: 43.238598, lng: 76.945377 };

  return (
    <div className="App">
      <header className="App-header">
        <div className="pikachu-banner">
          <img src="/pikachu.png" alt="Pikachu" className="pikachu-image" />
        </div>

        <div className="header-left">
          <h1>Mapachu</h1>
          <div className="timer">Time Left: {timeLeft} seconds</div>
          <div className="score">Score: {score}</div>
        </div>

        <div className="inventory-display">
          <div className="pokeball-count">
            <div className="pokeball-icon pokeball-normal" /> {inventory.normal}
          </div>
          <div className="pokeball-count">
            <div className="pokeball-icon pokeball-great" /> {inventory.great}
          </div>
          <div className="pokeball-count">
            <div className="pokeball-icon pokeball-ultra" /> {inventory.ultra}
          </div>
        </div>

      </header>




      {selectedSpawn && (
        <div className="pokemon-found-modal">
          <img src={selectedSpawn.pokemon.photoUrl} alt={selectedSpawn.pokemon.name} />
          <h2>{selectedSpawn.pokemon.name}</h2>
          <p>Type: {selectedSpawn.pokemon.type}</p>
          <p>Rarity: {selectedSpawn.pokemon.rarity}</p>
          <p>Strength: {selectedSpawn.pokemon.strength}</p>
          <p style={{ fontStyle: 'italic', marginTop: '8px', color: '#666' }}>
            You are near: {buildingName}
          </p>
          <p style={{ fontStyle: 'italic', color: '#444', marginTop: '8px' }}>
            It is: {buildingFact}
          </p>

          <div className="pokeball-options">
            {['normal', 'great', 'ultra'].map(type => (
              <button
                key={type}
                onClick={() => handlePokeballUse(type)}
                disabled={inventory[type] <= 0}
              >
                Use {type.charAt(0).toUpperCase() + type.slice(1)} Ball ({inventory[type]})
              </button>
            ))}
          </div>
        </div>
      )}

      <div className="map-container">
        {gameStarted && (
          <MapContainer
            center={almatyCenter}
            zoom={17}
            style={{ height: '80vh', width: '100%' }}
            zoomControl
            scrollWheelZoom
          >
            <MapBoundaryController center={almatyCenter} radiusMeters={1000} />
            <TileLayer
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
              attribution='&copy; OpenStreetMap contributors'
            />
            {spawns.map(spawn => (
              <Circle
                key={spawn.buildingId}
                center={spawn.position}
                radius={50}
                pathOptions={{ color: 'black', fillOpacity: 0 }}
                eventHandlers={{ click: () => handleCircleClick(spawn) }}
              />
            ))}
          </MapContainer>
        )}
      </div>
    </div>
  );
}

export default App;
