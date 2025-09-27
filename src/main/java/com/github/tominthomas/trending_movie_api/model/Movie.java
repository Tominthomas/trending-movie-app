package com.github.tominthomas.trending_movie_api.model;

import lombok.Data;

@Data
public class Movie {
    private Long id;
    private String title;
    private String overview;
    private String posterUrl;
    private String releaseDate;
    private Double voteAverage;
}