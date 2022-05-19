package com.sapient.movie.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public Movie getMovie(String movieName) {
		return movieRepository.findByMovieName(movieName);
	}
	
	public void save(Movie movie) {
		movieRepository.save(movie);
	}
}
