package com.sapient.theatre.data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.model.BookingEvent;
import com.sapient.model.SeatEvent;
import com.sapient.model.SeatStatus;
import com.sapient.model.SeatsDTO;

@Service
public class SeatService {

	@Autowired
	private SeatsRepository seatsRepository;
	
	public Set<Seats> getSeatsByTheatre(Long theatreId) {
		return seatsRepository.findAllByTheatre(theatreId);
	}
	
	public Set<Seats> getAvailableSeatsForTheatre(Long theatreId) {
		Set<Seats> seats = seatsRepository.findAllByTheatre(theatreId);
		return seats.stream().filter(seat -> seat.isAvailable()).collect(Collectors.toSet());
	}
	
	public void saveSeat(Seats seat) {
		seatsRepository.save(seat);
	}
	
	public void saveSeats(Set<Seats> seats) {
		seatsRepository.saveAll(seats);
	}
	
	public void deleteSeats(Set<Seats> seats) {
		seatsRepository.deleteAll(seats);
	}
	
	@Transactional
    public SeatEvent newBookingInventory(BookingEvent bookingEvent){
		SeatsDTO seatsDTO = null;
		SeatEvent seatEvent = null;
		List<String> seatNumbers = bookingEvent.getBookingDTO().getSeatNumbers();
		Long theatreId = bookingEvent.getBookingDTO().getTheatreId();
		Set<Seats> seats = getAvailableSeatsForTheatre(theatreId);
		if (!seats.isEmpty()) {
			seats.stream().filter(seat -> seatNumbers.contains(seat.getSeatNumber())).forEach(filteredSeat -> filteredSeat.setAvailable(false));
			saveSeats(seats);
			seatEvent = new SeatEvent(seatsDTO, SeatStatus.RESERVED);
		}
		else {
			seatEvent = new SeatEvent(seatsDTO, SeatStatus.REJECTED);
		}
		
		return seatEvent;
    }

    @Transactional
    public void cancelBookingInventory(BookingEvent bookingEvent){
    		Long theatreId = bookingEvent.getBookingDTO().getTheatreId();
    		Set<Seats> seats = getSeatsByTheatre(theatreId);
    		List<String> seatNumbers = bookingEvent.getBookingDTO().getSeatNumbers();
    		if (!seats.isEmpty()) {
    			seats.stream().filter(seat -> seatNumbers.contains(seat.getSeatNumber())).forEach(filteredSeat -> filteredSeat.setAvailable(true));
    		}
    }
}
