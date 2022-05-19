package com.sapient.theatre.data;

import java.util.Set;

public class TheatreShowsDTO {
	
	private Theatre theatre;
	private Set<Show> shows;

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Set<Show> getShows() {
		return shows;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}

}
