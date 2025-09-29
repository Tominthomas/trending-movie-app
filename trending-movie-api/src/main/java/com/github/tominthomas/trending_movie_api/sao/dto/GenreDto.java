package com.github.tominthomas.trending_movie_api.sao.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class GenreDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;
}