export async function getBuildingName(lat, lon) {
    const url = `https://nominatim.openstreetmap.org/reverse?lat=${lat}&lon=${lon}&format=json&addressdetails=1`;
  
    try {
      const response = await fetch(url, {
        headers: {
          'User-Agent': 'Mapachu/1.0 (contact@mapachu.kz)', // Replace with real
          'Accept-Language': 'en'
        }
      });
  
      if (!response.ok) throw new Error('Failed to fetch location');
      const data = await response.json();
  
      return data.display_name || 'Unknown place';
    } catch (error) {
      console.error('Geocoding error:', error);
      return 'Unknown location';
    }
  }
  