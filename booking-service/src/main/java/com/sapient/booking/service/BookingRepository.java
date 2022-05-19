package com.sapient.booking.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.booking.data.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	public Booking findByBookingDate(Date bookingDate);
	
	public List<Booking> findByBookingDateBetween(Date fromDate, Date toDate);
	
}
