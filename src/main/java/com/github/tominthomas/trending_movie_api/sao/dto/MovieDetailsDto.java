package com.github.tominthomas.trending_movie_api.sao.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieDetailsDto extends MovieSummaryDto {
    private String overview;

    @JsonProperty("genres")
    private List<GenreDto> genres;

    @JsonProperty("runtime")
    private Integer runtime;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("budget")
    private Long budget;

    @JsonProperty("revenue")
    private Long revenue;

    @JsonProperty("tagline")
    private String tagline;
}
