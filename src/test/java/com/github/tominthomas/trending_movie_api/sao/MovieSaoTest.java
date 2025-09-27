package com.github.tominthomas.trending_movie_api.sao;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.tominthomas.trending_movie_api.sao.dto.MovieDetailsDto;
import com.github.tominthomas.trending_movie_api.sao.dto.TrendingMoviesResponseDto;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class MovieSaoTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec uriSpec;
    @Mock
    private WebClient.RequestHeadersSpec headersSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    private MovieSao movieSao;

    @BeforeEach
    void setup() {
        movieSao = new MovieSao(webClient);
    }

    @Test
    void fetchTrending_returnsResponse() {
        TrendingMoviesResponseDto responseDto = new TrendingMoviesResponseDto();
        responseDto.setResults(List.of());

        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri("/trending/movie/{period}", "day")).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TrendingMoviesResponseDto.class)).thenReturn(Mono.just(responseDto));

        TrendingMoviesResponseDto result = movieSao.fetchTrending("day");

        assertEquals(responseDto, result);
    }

    @Test
    void fetchMovieDetails_returnsDetails() {
        MovieDetailsDto detailsDto = new MovieDetailsDto();
        detailsDto.setId(617126L);
        detailsDto.setTitle("The Fantastic 4");

        when(webClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri("/movie/{movieId}", "617126")).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(MovieDetailsDto.class)).thenReturn(Mono.just(detailsDto));

        MovieDetailsDto result = movieSao.fetchMovieDetails("617126");

        assertEquals(detailsDto, result);
        assertEquals("The Fantastic 4", result.getTitle());
    }
}