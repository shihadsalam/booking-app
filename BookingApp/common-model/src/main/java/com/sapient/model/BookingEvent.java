package com.sapient.model;

import java.util.Date;
import java.util.UUID;

public class BookingEvent {
	
	private final UUID eventId = UUID.randomUUID();
	private final Date date = new Date();
	private BookingDTO bookingDTO;
	private BookingStatus bookingStatus;
	
	public BookingEvent(BookingDTO bookingDTO, BookingStatus bookingStatus) {
		this.bookingDTO = bookingDTO;
		this.bookingStatus = bookingStatus;
	}
	
	public BookingDTO getBookingDTO() {
		return bookingDTO;
	}
	
	public void setBookingDTO(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}
	
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	public UUID getEventId() {
		return eventId;
	}
	
	public Date getDate() {
		return date;
	}

}
