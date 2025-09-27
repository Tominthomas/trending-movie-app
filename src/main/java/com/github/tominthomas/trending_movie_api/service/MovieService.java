package com.github.tominthomas.trending_movie_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.tominthomas.trending_movie_api.mapper.MovieDetailsMapper;
import com.github.tominthomas.trending_movie_api.mapper.MovieMapper;
import com.github.tominthomas.trending_movie_api.model.Movie;
import com.github.tominthomas.trending_movie_api.model.MovieDetails;
import com.github.tominthomas.trending_movie_api.sao.MovieSao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MovieSao movieSao;
    @Autowired
    private final MovieMapper movieMapper;
    @Autowired
    private final MovieDetailsMapper movieDetailsMapper;

    @Cacheable("trendingMovies")
    public List<Movie> getTrendingMovies(String period) {
        // Implementation to fetch trending movies from an external API
        return movieSao.fetchTrending(period)
                .getResults()
                .stream()
                .map(movieMapper::toDomain)
                .collect(Collectors.toList());
    }

    public MovieDetails getMovieDetails(String movieId) {
        // Implementation to fetch movie details from an external API
        return movieDetailsMapper.toDomain(movieSao.fetchMovieDetails(movieId));
    }
}