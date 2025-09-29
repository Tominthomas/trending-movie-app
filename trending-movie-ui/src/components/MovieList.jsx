import React from "react";
import MovieCard from "./MovieCard";
import { Box } from "@mui/material";

export default function MovieList({ movies }) {
  return (
    <Box sx={{ display: "flex", flexWrap: "wrap", justifyContent: "center" }}>
      {movies.map((movie) => (
        <MovieCard key={movie.id} movie={movie} />
      ))}
    </Box>
  );
}
