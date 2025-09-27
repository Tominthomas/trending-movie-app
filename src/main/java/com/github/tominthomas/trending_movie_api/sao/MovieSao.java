package com.github.tominthomas.trending_movie_api.sao;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.github.tominthomas.trending_movie_api.sao.dto.MovieDetailsDto;
import com.github.tominthomas.trending_movie_api.sao.dto.TrendingMoviesResponseDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
public class MovieSao {

    @Autowired
    private final WebClient webClient;

    public TrendingMoviesResponseDto fetchTrending(String period) {
        TrendingMoviesResponseDto responseDto = webClient.get()
                .uri("/trending/movie/{period}", period)
                .retrieve()
                .bodyToMono(TrendingMoviesResponseDto.class)
                .block();
        return responseDto;
    }

    public MovieDetailsDto fetchMovieDetails(String movieId) {
        MovieDetailsDto movieDetailsDto = webClient.get()
                .uri("/movie/{movieId}", movieId)
                .retrieve()
                .bodyToMono(MovieDetailsDto.class)
                .block();
        return movieDetailsDto;
    }
}