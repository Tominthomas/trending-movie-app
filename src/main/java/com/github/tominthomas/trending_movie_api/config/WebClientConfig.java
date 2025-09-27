package com.github.tominthomas.trending_movie_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${tmdb.api.base-url}")
    private String tmdbBaseUrl;

    @Value("${tmdb.api.token}")
    private String tmdbToken;

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl(tmdbBaseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tmdbToken)
                .build();
    }
}