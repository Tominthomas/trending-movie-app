import React, { useEffect, useState } from "react";
import {
  Typography,
  Grid,
  Container,
  ButtonGroup,
  Button,
  Box,
} from "@mui/material";
import { fetchTrendingMovies } from "../api/movieApi";
import MovieList from "../components/MovieList";

const TrendingMovies = () => {
  const [movies, setMovies] = useState([]);
  const [error, setError] = useState(null);
  const [timeWindow, setTimeWindow] = useState("day");

  useEffect(() => {
    const loadMovie = async () => {
      try {
        setError(null);
        const data = await fetchTrendingMovies(timeWindow);
        setMovies(data);
        setError(null);
      } catch (err) {
        setError(err.message);
      }
    };
    loadMovie();
  }, [timeWindow]);

  return (
    <Container>
      <Typography variant="h3" align="center">
        Trending Movies
      </Typography>
      <Box sx={{ display: "flex", justifyContent: "center", my: 3 }}>
        <ButtonGroup variant="contained">
          <Button
            onClick={() => setTimeWindow("day")}
            color={timeWindow === "day" ? "primary" : "inherit"}
          >
            Daily
          </Button>
          <Button
            onClick={() => setTimeWindow("week")}
            color={timeWindow === "week" ? "primary" : "inherit"}
          >
            Weekly
          </Button>
        </ButtonGroup>
      </Box>

      {error ? (
        <Typography color="error" align="center">
          {error}
        </Typography>
      ) : (
        <Grid container spacing={2}>
          <MovieList movies={movies} />
        </Grid>
      )}
    </Container>
  );
};

export default TrendingMovies;
