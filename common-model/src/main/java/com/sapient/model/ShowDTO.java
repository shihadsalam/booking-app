package com.sapient.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ShowDTO {

	private Long id;
	private String movieName;
	private Date timing;
	private String location;
	Set<TheatreDTO> theatreDTOs = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getTiming() {
		return timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<TheatreDTO> getTheatres() {
		return theatreDTOs;
	}

	public void setTheatres(Set<TheatreDTO> theatreDTOs) {
		this.theatreDTOs = theatreDTOs;
	}


}
