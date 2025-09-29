package com.github.tominthomas.trending_movie_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.tominthomas.trending_movie_api.model.MovieDetails;
import com.github.tominthomas.trending_movie_api.sao.dto.GenreDto;
import com.github.tominthomas.trending_movie_api.sao.dto.MovieDetailsDto;

@Mapper(componentModel = "spring")
public interface MovieDetailsMapper {
    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "posterUrl", expression = "java(\"https://image.tmdb.org/t/p/w500\" + dto.getPosterPath())")
    MovieDetails toDomain(MovieDetailsDto dto);

    default List<String> mapGenres(List<GenreDto> genres) {
        if (genres == null)
            return null;
        return genres.stream().map(GenreDto::getName).toList();
    }
}
