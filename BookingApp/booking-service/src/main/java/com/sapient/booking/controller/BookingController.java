package com.sapient.booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.booking.data.Booking;
import com.sapient.booking.service.BookingService;
import com.sapient.model.BookingDTO;

@RestController
@RequestMapping("/v1/api")
public class BookingController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking/add")
	public ResponseEntity<String> createBooking(@RequestBody BookingDTO bookingDTO) {
		logger.info("Invoked booking api to book movie ticket {}", bookingDTO);
		try {
			bookingService.createBooking(bookingDTO);
			return ResponseEntity.ok("Booking Success!!!");
		}
		catch(Exception e) {
			logger.info("Exception occured while saving the booking", e);
			return ResponseEntity.ok("Booking Failed");
		}
	}
	
	@PostMapping("/booking/cancel")
	public ResponseEntity<String> cancelBooking(@RequestBody BookingDTO bookingDTO) {
		logger.info("Invoked booking api to cancel movie ticket {}", bookingDTO);
		try {
			bookingService.cancel(bookingDTO);
			return ResponseEntity.ok("Booking Cancellation Success!!!");
		}
		catch(Exception e) {
			logger.info("Exception occured while cancelling the booking", e);
			return ResponseEntity.ok("Booking Cancellation Failed");
		}
	}
	
	@GetMapping("/booking/fetch-by-date")
	public ResponseEntity<Booking> getBookingDetails(@RequestParam String bookingDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bookingDate);
		logger.info("Fetching booking details by bookingDate {}", bookingDate);
		try {
			return ResponseEntity.ok(bookingService.getBookingByDate(date));
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching the booking record", e);
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
}
