package com.sapient.theatre.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "show")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "movie_name")
	private String movieName;

	@Column(name = "timing")
	private Date timing;
	
	@Column(name = "location")
	private String location;

    @ManyToMany(mappedBy = "shows", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	Set<Theatre> theatres = new HashSet<>();
	
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

	public Set<Theatre> getTheatres() {
		return theatres;
	}

	public void setTheatres(Set<Theatre> theatres) {
		this.theatres = theatres;
	}


}
