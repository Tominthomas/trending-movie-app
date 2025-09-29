import axios from 'axios';

const API_BASE = 'http://localhost:8080/api'; // your Spring Boot backend

export const fetchTrendingMovies = async () => {
  const response = await axios.get(`${API_BASE}/movies/trending/day`);
  return response.data;
};

export const fetchMovieDetails = async (id) => {
  const response = await axios.get(`${API_BASE}/movies/${id}`);
  return response.data;
};