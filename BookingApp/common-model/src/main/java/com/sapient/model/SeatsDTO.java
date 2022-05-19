package com.sapient.model;

public class SeatsDTO {

	private Long id;
	private Long bookingId;
	private String seatNumber;
	private TheatreDTO theatreDTO;
	private boolean isAvailable = true;

	public Long getId() {
		return id;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public TheatreDTO getTheatre() {
		return theatreDTO;
	}

	public void setTheatre(TheatreDTO theatreDTO) {
		this.theatreDTO = theatreDTO;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public TheatreDTO getTheatreDTO() {
		return theatreDTO;
	}

	public void setTheatreDTO(TheatreDTO theatreDTO) {
		this.theatreDTO = theatreDTO;
	}
	
}
