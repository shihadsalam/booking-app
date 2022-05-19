package com.sapient.movie.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="movie_language")
	private String movieLanguage;
	
	@Column(name="actors")
	private String actors;
	
	@Column(name="crews")
	private String crews;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getCrews() {
		return crews;
	}

	public void setCrews(String crews) {
		this.crews = crews;
	}

	public int getId() {
		return id;
	}
	
}
