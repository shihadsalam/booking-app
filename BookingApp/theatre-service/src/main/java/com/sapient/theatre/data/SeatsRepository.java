package com.sapient.theatre.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats, Integer> {
	
	public Set<Seats> findAllByTheatre(Long theatreId);
	
}
