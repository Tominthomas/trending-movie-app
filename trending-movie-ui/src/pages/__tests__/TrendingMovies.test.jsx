// src/pages/__tests__/TrendingMovies.test.jsx
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import TrendingMovies from "../TrendingMovies";
import { MemoryRouter } from "react-router";
import axios from "axios";
import { FavoritesProvider } from "../../hooks/FavoritesContext";

jest.mock("axios"); // Mock Axios

const mockMoviesDay = [
  { id: 1, title: "Movie 1", posterUrl: "url1", voteAverage: 7.5 },
  { id: 2, title: "Movie 2", posterUrl: "url2", voteAverage: 8.2 },
];

const mockMoviesWeek = [
  { id: 3, title: "Movie 3", posterUrl: "url3", voteAverage: 6.5 },
  { id: 4, title: "Movie 4", posterUrl: "url4", voteAverage: 9.0 },
];

describe("TrendingMovies component", () => {
  beforeEach(() => {
    axios.get.mockClear();
  });

  test("renders heading and daily trending movies", async () => {
    axios.get.mockResolvedValueOnce({ data: mockMoviesDay });

    render(
      <FavoritesProvider>
        <MemoryRouter>
          <TrendingMovies />
        </MemoryRouter>
      </FavoritesProvider>
    );

    // Check heading
    expect(screen.getByText(/Trending Movies/i)).toBeInTheDocument();

    // Wait for movies to load
    await waitFor(() => {
      expect(screen.getByText("Movie 1")).toBeInTheDocument();
      expect(screen.getByText("Movie 2")).toBeInTheDocument();
    });
  });

  test("switches to weekly trending movies on button click", async () => {
    // First call: daily
    axios.get.mockResolvedValueOnce({ data: mockMoviesDay });
    // Second call: weekly
    axios.get.mockResolvedValueOnce({ data: mockMoviesWeek });

    render(
      <FavoritesProvider>
        <MemoryRouter>
          <TrendingMovies />
        </MemoryRouter>
      </FavoritesProvider>
    );

    // Wait for daily movies
    await waitFor(() => {
      expect(screen.getByText("Movie 1")).toBeInTheDocument();
    });

    // Click "Weekly" button
    fireEvent.click(screen.getByText(/Weekly/i));

    // Wait for weekly movies
    await waitFor(() => {
      expect(screen.getByText("Movie 3")).toBeInTheDocument();
      expect(screen.getByText("Movie 4")).toBeInTheDocument();
    });
  });

  test("shows error message when API fails", async () => {
    axios.get.mockRejectedValueOnce(new Error("API failure"));

    render(
      <FavoritesProvider>
        <MemoryRouter>
          <TrendingMovies />
        </MemoryRouter>
      </FavoritesProvider>
    );

    await waitFor(() => {
      expect(
        screen.getByText(/Failed to load trending movies/i)
      ).toBeInTheDocument();
    });
  });
});
