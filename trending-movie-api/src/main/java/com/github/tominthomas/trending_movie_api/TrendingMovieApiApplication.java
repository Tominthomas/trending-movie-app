package com.github.tominthomas.trending_movie_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrendingMovieApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendingMovieApiApplication.class, args);
	}

}
