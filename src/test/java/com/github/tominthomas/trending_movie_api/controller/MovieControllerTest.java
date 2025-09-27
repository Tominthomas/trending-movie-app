package com.github.tominthomas.trending_movie_api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.github.tominthomas.trending_movie_api.model.Movie;
import com.github.tominthomas.trending_movie_api.model.MovieDetails;
import com.github.tominthomas.trending_movie_api.service.MovieService;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void getTrending_returnsMovies() throws Exception {
        // Mock service
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("Movie One");
        movie1.setVoteAverage(8.0);

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("Movie Two");
        movie2.setVoteAverage(7.5);

        when(movieService.getTrendingMovies("day")).thenReturn(List.of(movie1, movie2));

        // Perform request and verify
        mockMvc.perform(get("/api/movies/trending/day"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Movie One"))
                .andExpect(jsonPath("$[0].voteAverage").value(8.0))
                .andExpect(jsonPath("$[1].title").value("Movie Two"))
                .andExpect(jsonPath("$[1].voteAverage").value(7.5));
    }

    @Test
    void getMovieDetails_returnsDetails() throws Exception {
        MovieDetails details = new MovieDetails();
        details.setId(617126L);
        details.setTitle("The Fantastic 4");
        details.setVoteAverage(7.5);

        when(movieService.getMovieDetails("617126")).thenReturn(details);

        mockMvc.perform(get("/api/movies/617126"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Fantastic 4"))
                .andExpect(jsonPath("$.voteAverage").value(7.5));
    }
}