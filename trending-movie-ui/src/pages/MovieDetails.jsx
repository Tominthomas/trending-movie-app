import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { fetchMovieDetails } from '../api/movieApi';
import { Typography, Container, Button, Box} from '@mui/material';

const MovieDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    fetchMovieDetails(id).then(setMovie);
  }, [id]);

  if (!movie) return <Typography>Loading...</Typography>;

  return (
    <Container sx={{ mt: 4, position: 'relative' }}>
      {/* Back Button in Top-Left */}
      <Box sx={{ position: 'absolute', top: 16, left: 16 }}>
        <Button
          variant="outlined"
          onClick={() => navigate(-1)}
        >
          ← Back
        </Button>
      </Box>

      {/* Centered Content */}
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', mt: 6 }}>
        <Typography variant="h3" gutterBottom>{movie.title}</Typography>
        <img
          src={movie.posterUrl}
          alt={movie.title}
          style={{ maxWidth: '300px', marginBottom: '1rem' }}
        />
        <Typography variant="body1" paragraph>{movie.overview}</Typography>
        <Typography variant="body2">⭐ {movie.voteAverage}</Typography>
      </Box>
    </Container>
  );
};

export default MovieDetails;