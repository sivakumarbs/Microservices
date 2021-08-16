package com.app.service;

import java.util.List;

import com.app.model.Movie;

public interface IMovieService {
	
	public Integer saveMovie(Movie movie);
	public Movie getMovie(Integer movieId);
	public Movie findMovie(String code);
	public List<Movie> getAllMovies();

}
