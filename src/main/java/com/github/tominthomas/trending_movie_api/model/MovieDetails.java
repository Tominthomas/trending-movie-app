package com.github.tominthomas.trending_movie_api.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieDetails extends Movie {
    private String overview;
    private List<String> genres; // flattened
    private Integer runtime;
    private String homepage;
    private Long budget;
    private Long revenue;
    private String tagline;
}
