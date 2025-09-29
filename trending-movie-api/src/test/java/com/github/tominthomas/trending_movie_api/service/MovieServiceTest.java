package com.github.tominthomas.trending_movie_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.tominthomas.trending_movie_api.mapper.MovieDetailsMapper;
import com.github.tominthomas.trending_movie_api.mapper.MovieMapper;
import com.github.tominthomas.trending_movie_api.model.Movie;
import com.github.tominthomas.trending_movie_api.model.MovieDetails;
import com.github.tominthomas.trending_movie_api.sao.MovieSao;
import com.github.tominthomas.trending_movie_api.sao.dto.MovieDetailsDto;
import com.github.tominthomas.trending_movie_api.sao.dto.MovieSummaryDto;
import com.github.tominthomas.trending_movie_api.sao.dto.TrendingMoviesResponseDto;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieSao movieSao;

    private MovieMapper movieMapper;
    private MovieDetailsMapper movieDetailsMapper;
    private MovieService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movieMapper = Mappers.getMapper(MovieMapper.class);
        movieDetailsMapper = Mappers.getMapper(MovieDetailsMapper.class);
        service = new MovieService(movieSao, movieMapper, movieDetailsMapper);
    }

    @Test
    void getMovieDetails_returnsDetails() {
        MovieDetailsDto detailsDto = new MovieDetailsDto();
        detailsDto.setId(617126L);
        detailsDto.setTitle("The Fantastic 4");
        detailsDto.setVoteAverage(7.5);
        detailsDto.setOverview("Four young outsiders teleport to an alternate and dangerous universe...");
        when(movieSao.fetchMovieDetails("617126")).thenReturn(detailsDto);

        MovieDetails result = service.getMovieDetails("617126");

        assertEquals("The Fantastic 4", result.getTitle());
        assertEquals(7.5, result.getVoteAverage());

    }

    @Test
    void getTrendingMovies_returnsMappedMovies() {
        // Create dummy DTOs
        MovieSummaryDto dto1 = new MovieSummaryDto();
        dto1.setId(1L);
        dto1.setTitle("Movie One");
        dto1.setVoteAverage(8.0);

        MovieSummaryDto dto2 = new MovieSummaryDto();
        dto2.setId(2L);
        dto2.setTitle("Movie Two");
        dto2.setVoteAverage(7.5);

        TrendingMoviesResponseDto responseDto = new TrendingMoviesResponseDto();
        responseDto.setResults(List.of(dto1, dto2));

        // Mock the SAO call
        when(movieSao.fetchTrending("day")).thenReturn(responseDto);

        // Call the service
        List<Movie> movies = service.getTrendingMovies("day");

        // Verify mapping and results
        assertEquals(2, movies.size());
        assertEquals("Movie One", movies.get(0).getTitle());
        assertEquals(8.0, movies.get(0).getVoteAverage());
        assertEquals("Movie Two", movies.get(1).getTitle());
        assertEquals(7.5, movies.get(1).getVoteAverage());
    }
}
