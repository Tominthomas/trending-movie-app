import React from "react";
import { BrowserRouter, Routes, Route } from "react-router";
import TrendingMovies from "./pages/TrendingMovies";
import MovieDetails from "./pages/MovieDetails";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<TrendingMovies />} />
        <Route path="/:id" element={<MovieDetails />} />
      </Routes>
    </BrowserRouter>
  );
}
