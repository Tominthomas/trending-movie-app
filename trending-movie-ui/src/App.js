import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TrendingMovies from "./pages/TrendingMovies";
import MovieDetails from "./pages/MovieDetails";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<TrendingMovies />} />
        <Route path="/:id" element={<MovieDetails />} />
      </Routes>
    </Router>
  );
}
