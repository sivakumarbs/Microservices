package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Movie;


public interface MovieRepository extends JpaRepository<Movie,Integer> {

	public Optional<Movie> findByMovieCode(String movieCode);
	public Optional<Movie> findByMovieId(String movieId);
}
