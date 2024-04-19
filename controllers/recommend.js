const API_KEY = process.env.API_KEY
const axios = require("axios")

const getPlaceDetails = async (placeName) => {
  try {
    const apiKey = API_KEY;
    const apiUrl = `https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=${encodeURIComponent(
      placeName
    )}&inputtype=textquery&fields=name,formatted_address,rating,price_level,user_ratings_total,photos,opening_hours&key=${apiKey}`;

    const response = await axios.get(apiUrl);

    if (response.data.status === "OK") {
      const placeDetails = response.data.candidates[0];
      return {
        name: placeDetails?.name,
        address: placeDetails?.formatted_address,
        rating: placeDetails?.rating,
        priceLevel: placeDetails?.price_level,
        totalRatings: placeDetails?.user_ratings_total,
        openingHours: placeDetails?.opening_hours,
        photos: placeDetails?.photos,
      };
    } else {
      throw new Error("Place details not found");
    }
  } catch (error) {
    console.error("Error fetching place details:", error);
    throw error;
  }
};

const getRecommendations = async (req, res) => {
  const { persons, budget, days } = req.body;
  const maxi = budget / (persons * days);
  const places = [
    { name: "Kanpur", maxBudget: 5000 },
    { name: "Jaipur", maxBudget: 10000 },
    { name: "Goa", maxBudget: 15000 },
    { name: "Dehradun", maxBudget: 11000 },
    { name: "Safari Adventure", maxBudget: 18000 },
    { name: "Delhi", maxBudget: 25000 },
    { name: "Shimla", maxBudget: 23000 },
    { name: "Pune", maxBudget: 21000 },
    { name: "Jammy and Kashmir", maxBudget: 2000 },
  ];

  const filteredPlaces = places
    .filter((place) => maxi < place.maxBudget)
    .map(({ name }) => name);

  const result = [];
  for (const placeName of filteredPlaces) {
    const details = await getPlaceDetails(placeName);
    result.push(details);
  }

  res.status(200).json(result);
};

module.exports = {
  getRecommendations,
};
