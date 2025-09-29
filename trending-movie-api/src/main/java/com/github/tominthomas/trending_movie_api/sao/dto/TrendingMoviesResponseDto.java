package com.github.tominthomas.trending_movie_api.sao.dto;

import java.util.List;
import lombok.Data;

@Data
public class TrendingMoviesResponseDto {
    private Integer page;
    private List<MovieSummaryDto> results;
    private Integer totalPages;
    private Integer totalResults;
}