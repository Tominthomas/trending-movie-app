package com.github.tominthomas.trending_movie_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.tominthomas.trending_movie_api.model.Movie;
import com.github.tominthomas.trending_movie_api.sao.dto.MovieSummaryDto;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "posterUrl", expression = "java(\"https://image.tmdb.org/t/p/w500\" + dto.getPosterPath())")
    Movie toDomain(MovieSummaryDto dto);
}