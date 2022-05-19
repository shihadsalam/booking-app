package com.sapient.theatre.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.theatre.data.SeatService;
import com.sapient.theatre.data.Seats;
import com.sapient.theatre.data.Show;
import com.sapient.theatre.data.Theatre;
import com.sapient.theatre.data.TheatreService;
import com.sapient.theatre.data.TheatreShowsDTO;

@RestController
@RequestMapping("/v1/api")
public class TheatreController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TheatreService theatreService;
	
	@Autowired
	private SeatService seatService;
	
	@PostMapping("/theatre/add")
	public ResponseEntity<String> addTheatre(@RequestBody Theatre theatre) {
		logger.info("Invoked theatre api to add new theatre {}", theatre);
		try {
			theatreService.save(theatre);
			return ResponseEntity.ok("Theatre succesfully added");
		}
		catch(Exception e) {
			logger.info("Exception occured while adding the theatre", e);
			return ResponseEntity.ok("Theatre addition failed");
		}
	}
	
	@PostMapping("/theatre/add-update-shows")
	public ResponseEntity<String> addShows(@RequestBody TheatreShowsDTO theatreShows) {
		Theatre theatre = theatreShows.getTheatre();
		logger.info("Invoked theatre api to add new shows to theatre", theatre);
		theatre.addOrUpdateShows(theatreShows.getShows());
		try {
			theatreService.save(theatre);
			return ResponseEntity.ok("Theatre shows succesfully added");
		}
		catch(Exception e) {
			logger.info("Exception occured while adding the show", e);
			return ResponseEntity.ok("Show addition failed");
		}
	}
	
	@DeleteMapping("/theatre/delete-shows")
	public ResponseEntity<String> deleteShows(@RequestBody TheatreShowsDTO theatreShows) {
		Theatre theatre = theatreShows.getTheatre();
		logger.info("Invoked theatre api to delete shows from theatre", theatre);
		theatre.deleteShows(theatreShows.getShows());
		try {
			theatreService.delete(theatre);
			return ResponseEntity.ok("Theatre shows succesfully deleted");
		}
		catch(Exception e) {
			logger.info("Exception occured while deleting the show", e);
			return ResponseEntity.ok("Show deletion failed");
		}
	}
	
	@GetMapping("/theatre/fetch-by-date")
	public ResponseEntity<List<Theatre>> getTheatreByShowDetails(@RequestBody Set<Show> shows) {
		logger.info("Fetching theatre details by shows {}", shows);
		try {
			return ResponseEntity.ok(theatreService.getTheatresByShows(shows));
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching the theatre details", e);
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
	
	@GetMapping("/theatre/fetch-available-seats")
	public ResponseEntity<Set<Seats>> getAvailableSeats(@RequestBody Theatre theatre) {
		logger.info("Fetching available seat details for theatre {}", theatre);
		try {
			return ResponseEntity.ok(seatService.getAvailableSeatsForTheatre(theatre.getId()));
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching the available seat details", e);
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
	@PostMapping("/theatre/add-update-seat")
	public ResponseEntity<String> allocateSeats(@RequestBody Seats seat) {
		logger.info("Invoked theatre api to add new seats {}", seat);
		try {
			seatService.saveSeat(seat);
			return ResponseEntity.ok("Seats succesfully added");
		}
		catch(Exception e) {
			logger.info("Exception occured while adding the seat", e);
			return ResponseEntity.ok("Seat addition failed");
		}
	}
	
	@DeleteMapping("/theatre/delete-seats")
	public ResponseEntity<String> deleteSeats(@RequestBody Set<Seats> seats) {
		logger.info("Invoked theatre api to delete seats {}", seats);
		try {
			seatService.deleteSeats(seats);
			return ResponseEntity.ok("Theatre seats succesfully deleted");
		}
		catch(Exception e) {
			logger.info("Exception occured while deleting the seats", e);
			return ResponseEntity.ok("Seats deletion failed");
		}
	}
}
