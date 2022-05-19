package com.sapient.model;

import java.util.HashSet;
import java.util.Set;

public class TheatreDTO {

	private Long id;
	private String theatreName;
	Set<ShowDTO> shows = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Set<ShowDTO> getShows() {
		return shows;
	}

	public void setShows(Set<ShowDTO> shows) {
		this.shows = shows;
	}
	
}
