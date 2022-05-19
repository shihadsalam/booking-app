package com.sapient.theatre.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

	@Autowired
	private TheatreRepository theatreRepository;
	
	public Theatre getTheatre(String theatreName) {
		return theatreRepository.findByTheatreName(theatreName);
	}
	
	public List<Theatre> getTheatresByShows(Set<Show> shows) {
		List<Theatre> theatres = theatreRepository.findAll();
		List<Theatre> theatreListByShows = new ArrayList<Theatre>();
		for(Show show : shows) {
			for(Theatre theatre : theatres) {
				if (theatre.getShows().contains(show)) {
					theatreListByShows.add(theatre);
				}
			}
		}
		return theatreListByShows;
	}
	
	public void save(Theatre movie) {
		theatreRepository.save(movie);
	}
	
	public void delete(Theatre movie) {
		theatreRepository.delete(movie);
	}
}
