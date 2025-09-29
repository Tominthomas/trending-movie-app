package com.github.tominthomas.trending_movie_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tominthomas.trending_movie_api.service.MovieService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // allow FE local app to hit API
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @GetMapping("/trending/{period}")
    public Object getTrending(@PathVariable String period) {
        return movieService.getTrendingMovies(period);
    }

    @GetMapping("/{id}")
    public Object getMovieDetails(@PathVariable String id) {
        return movieService.getMovieDetails(id);
    }
}
