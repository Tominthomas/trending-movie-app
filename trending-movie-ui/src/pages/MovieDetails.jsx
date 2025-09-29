import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { fetchMovieDetails } from "../api/movieApi";
import { Typography, Container, Button, Box } from "@mui/material";

const MovieDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [movie, setMovie] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadMovie = async () => {
      setError(null);
      try {
        const data = await fetchMovieDetails(id);
        setMovie(data);
      } catch (err) {
        setError(err.message);
      }
    };
    loadMovie();
  }, [id]);

  if (!movie && !error) return <Typography>Loading...</Typography>;

  return (
    <Container sx={{ mt: 4, position: "relative" }}>
      <Box sx={{ position: "absolute", top: 16, left: 16 }}>
        <Button variant="outlined" onClick={() => navigate("/")}>
          ← Back
        </Button>
      </Box>

      {error ? (
        <Typography color="error" align="center">
          {error}
        </Typography>
      ) : (
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            mt: 6,
          }}
        >
          <Typography variant="h3" gutterBottom>
            {movie.title}
          </Typography>
          <img
            src={movie.posterUrl}
            alt={movie.title}
            style={{ maxWidth: "300px", marginBottom: "1rem" }}
          />
          <Typography variant="body1" paragraph>
            {movie.overview}
          </Typography>
          <Typography variant="body2">⭐ {movie.voteAverage}</Typography>
        </Box>
      )}
    </Container>
  );
};

export default MovieDetails;
