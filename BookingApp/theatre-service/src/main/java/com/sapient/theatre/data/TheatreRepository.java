package com.sapient.theatre.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
	
	public Theatre findByTheatreName(String theatreName);

}
