import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/movies"; // Local Spring Boot endpoint

export const fetchTrendingMovies = async (timeWindow) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/trending/${timeWindow}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching trending movies:", error);
    throw new Error("Failed to load trending movies. Please try again.");
  }
};

export const fetchMovieDetails = async (id) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching movie ${id}:`, error);
    throw new Error("Failed to load movie details.");
  }
};