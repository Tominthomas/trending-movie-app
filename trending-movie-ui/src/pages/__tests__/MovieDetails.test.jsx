import React from "react";
import { render, screen, waitFor, fireEvent } from "@testing-library/react";
import MovieDetails from "../MovieDetails";
import { useParams, useNavigate } from "react-router";
import * as movieApi from "../../api/movieApi";

// Mock useParams and useNavigate
jest.mock("react-router", () => ({
  useParams: jest.fn(),
  useNavigate: jest.fn(),
}));

describe("MovieDetails page", () => {
  const mockNavigate = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
    useNavigate.mockReturnValue(mockNavigate);
    useParams.mockReturnValue({ id: "123" });
  });

  test("renders loading state initially", () => {
    render(<MovieDetails />);
    expect(screen.getByText(/Loading.../i)).toBeInTheDocument();
  });

  test("renders movie details when fetch succeeds", async () => {
    const mockMovie = {
      id: 123,
      title: "Test Movie",
      posterUrl: "test-poster.jpg",
      overview: "This is a test movie.",
      voteAverage: 8.5,
    };

    jest.spyOn(movieApi, "fetchMovieDetails").mockResolvedValueOnce(mockMovie);

    render(<MovieDetails />);

    // Wait for the movie title to appear
    await waitFor(() => {
      expect(screen.getByText("Test Movie")).toBeInTheDocument();
    });

    expect(screen.getByText(/This is a test movie/i)).toBeInTheDocument();
    expect(screen.getByText(/⭐ 8.5/i)).toBeInTheDocument();
    expect(screen.getByRole("img", { name: /Test Movie/i })).toHaveAttribute(
      "src",
      "test-poster.jpg"
    );
  });

  test("renders error message when fetch fails", async () => {
    jest
      .spyOn(movieApi, "fetchMovieDetails")
      .mockRejectedValueOnce(new Error("Failed to fetch"));

    render(<MovieDetails />);

    await waitFor(() => {
      expect(screen.getByText(/Failed to fetch/i)).toBeInTheDocument();
    });
  });

  test("back button navigates to home page", async () => {
    const mockMovie = {
      id: 123,
      title: "Test Movie",
      posterUrl: "",
      overview: "",
      voteAverage: 0,
    };
    jest.spyOn(movieApi, "fetchMovieDetails").mockResolvedValueOnce(mockMovie);

    render(<MovieDetails />);

    await waitFor(() => {
      expect(screen.getByText("Test Movie")).toBeInTheDocument();
    });

    fireEvent.click(screen.getByRole("button", { name: /← Back/i }));
    expect(mockNavigate).toHaveBeenCalledWith("/");
  });
});
