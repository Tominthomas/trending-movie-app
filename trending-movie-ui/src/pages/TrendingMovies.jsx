import React, { useEffect, useState } from 'react';
import { Typography, Grid, Container } from '@mui/material';
import MovieCard from '../components/MovieCard';
import { fetchTrendingMovies } from '../api/movieApi';
import MovieList from '../components/MovieList';

const TrendingMovies = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchTrendingMovies()
      .then(setMovies)
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <Typography>Loading...</Typography>;

  return (
    <Container>
      <Typography variant="h3" align='center'>Trending Movies</Typography>
      <Grid container spacing={2}>
        <MovieList movies={movies} />
      </Grid>
    </Container>
  );
};

export default TrendingMovies;