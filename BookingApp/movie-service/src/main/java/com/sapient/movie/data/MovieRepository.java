package com.sapient.movie.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	public Movie findByMovieName(String movieName);
	
}
