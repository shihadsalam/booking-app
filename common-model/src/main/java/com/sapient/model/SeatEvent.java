package com.sapient.model;

import java.util.Date;
import java.util.UUID;

public class SeatEvent {
	
	private final UUID eventId = UUID.randomUUID();
	private final Date date = new Date();
	private SeatsDTO seatsDTO;
	private SeatStatus seatStatus;
	
	public SeatEvent(SeatsDTO seatsDTO, SeatStatus seatStatus) {
		this.seatsDTO = seatsDTO;
		this.seatStatus = seatStatus;
	}

	public SeatsDTO getSeatsDTO() {
		return seatsDTO;
	}
	
	public void setSeatsDTO(SeatsDTO seatsDTO) {
		this.seatsDTO = seatsDTO;
	}
	
	public SeatStatus getSeatStatus() {
		return seatStatus;
	}
	
	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	public UUID getEventId() {
		return eventId;
	}
	
	public Date getDate() {
		return date;
	}

}
