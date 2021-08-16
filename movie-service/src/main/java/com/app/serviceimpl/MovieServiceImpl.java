package com.app.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.service.IMovieService;

@Service
public class MovieServiceImpl implements IMovieService{

	@Autowired
	private MovieRepository repo;
	
	@Transactional
	@Override
	public Integer saveMovie(Movie movie) {
		movie.setDate(new Date());
		Movie m = repo.save(movie);
		return m.getMovieId();
	}

	@Transactional(readOnly = true)
	@Override
	public Movie getMovie(Integer movieId) {
		Optional<Movie> mid = repo.findById(movieId);
		if(mid.isPresent()) {
			return mid.get();
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Movie findMovie(String code) {
		Optional<Movie> movie = repo.findByMovieCode(code);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Movie> getAllMovies() {
		return repo.findAll();
	}

	
}
