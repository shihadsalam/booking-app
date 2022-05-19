package com.sapient.movie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.movie.data.Movie;
import com.sapient.movie.data.MovieService;

@RestController
@RequestMapping("/v1/api")
public class MovieController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movie/add")
	public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
		logger.info("Invoked movie api to add new movie {}", movie);
		try {
			movieService.save(movie);
			return ResponseEntity.ok("Movie succesfully added");
		}
		catch(Exception e) {
			logger.info("Exception occured while adding the movie", e);
			return ResponseEntity.ok("Movie addition failed");
		}
	}
}
