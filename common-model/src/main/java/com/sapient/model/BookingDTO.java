package com.sapient.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class BookingDTO {
	
	private Long id;
	private CustomerDTO customer;
	private ShowDTO show;
	private List<SeatsDTO> seat;
	private Date bookingDate;
	private BigDecimal ticketPrice;
	private Long theatreId;
	private List<String> seatNumbers;
	private BookingStatus bookingStatus;
	private SeatStatus seatStatus;
	
	public CustomerDTO getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	public ShowDTO getShow() {
		return show;
	}
	
	public void setShow(ShowDTO show) {
		this.show = show;
	}
	
	public List<SeatsDTO> getSeat() {
		return seat;
	}
	
	public void setSeat(List<SeatsDTO> seat) {
		this.seat = seat;
	}
	
	public Date getBookingDate() {
		return bookingDate;
	}
	
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}
	
	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public Long getId() {
		return id;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public List<String> getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(List<String> seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

}
