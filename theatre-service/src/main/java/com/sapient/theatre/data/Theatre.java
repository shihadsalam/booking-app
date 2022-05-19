package com.sapient.theatre.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "theatre")
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "theatre_name")
	private String theatreName;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "theatre_show", joinColumns = { @JoinColumn(name = "theatre_id") }, inverseJoinColumns = { @JoinColumn(name = "show_id") })
	Set<Show> shows = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Set<Show> getShows() {
		return shows;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}
	
	public void addOrUpdateShows(Set<Show> shows) {
		this.getShows().addAll(shows);
		shows.stream().forEach(show -> show.getTheatres().add(this));
	}
	
	public void deleteShows(Set<Show> shows) {
		this.getShows().removeAll(shows);
		shows.stream().forEach(show -> show.getTheatres().add(this));
	}
	
}
