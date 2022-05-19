package com.sapient.booking.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.booking.data.Booking;
import com.sapient.model.BookingDTO;
import com.sapient.model.BookingStatus;
import com.sapient.model.SeatsDTO;
import com.sapient.model.TheatreDTO;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingStatusPublisher bookingStatusPublisher;
	
	@Value("${booking.discount.limit}")
	private Integer bookingLimitForDiscount;
	
	private static final LocalTime AFTER_NOON = LocalTime.of(12, 0, 0);
	private static final LocalTime MID_NIGHT = LocalTime.of(23, 0, 0);
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public Booking getBooking(Long bookingId) {
		return bookingRepository.findById(bookingId).get();
	}
	
	public Booking getBookingByDate(Date bookingDate) {
		return bookingRepository.findByBookingDate(bookingDate);
	}
	
	public List<Booking> getByBookingDateBetween(Date fromDate, Date toDate) {
		return bookingRepository.findByBookingDateBetween(fromDate, toDate);
	}
	
	@Transactional
	public void createBooking(BookingDTO bookingDTO) throws ParseException {
		Booking booking = buildBookingData(bookingDTO);
		bookingRepository.save(booking);
		bookingStatusPublisher.raiseBookingEvent(bookingDTO, BookingStatus.ORDER_CREATED);
	}

	public void cancel(BookingDTO bookingDTO) throws ParseException {
		bookingRepository.delete(bookingRepository.findById(bookingDTO.getId()).get());
		bookingStatusPublisher.raiseBookingEvent(bookingDTO, BookingStatus.ORDER_CANCELLED);
	}
	
	private Booking buildBookingData(BookingDTO bookingDTO) throws ParseException {
		Booking booking = new Booking();
		booking.setBookingDate(bookingDTO.getBookingDate());
		BeanUtils.copyProperties(bookingDTO.getCustomer(), booking.getCustomer());
		booking.setMovieName(bookingDTO.getShow().getMovieName());
		booking.setSeatNumbers(getSeats(bookingDTO.getSeat()));
		booking.setShowDate(bookingDTO.getShow().getTiming());
		
		TheatreDTO theatreDTO = bookingDTO.getSeat().get(0).getTheatre();
		booking.setTheatreId(theatreDTO.getId());
		booking.setTheatreName(theatreDTO.getTheatreName());
		booking.setTicketPrice(bookingDTO.getTicketPrice());
		
		// Apply discounts and create the booking
		applyDiscount(booking);
		
		return booking;
	}
	
	private String getSeats(List<SeatsDTO> seatsDTO) {
		List<String> seatDTOs = seatsDTO.stream().map(seat -> seat.getSeatNumber()).collect(Collectors.toList());
		return String.join(",", seatDTOs);
	}
	
	private Booking applyDiscount(Booking booking) throws ParseException {
		Date fromDate = new SimpleDateFormat(DATE_FORMAT).parse(String.valueOf(System.currentTimeMillis()));
		Date toDate = new SimpleDateFormat(DATE_FORMAT).parse(LocalDateTime.from(fromDate.toInstant()).plusDays(1).toString());
		List<Booking> bookings = getByBookingDateBetween(fromDate, toDate);
		
		// 50% Discount for 3rd Booking for the day
		if (bookings.size() == bookingLimitForDiscount) {
			BigDecimal discountedPrice = booking.getTicketPrice().multiply(new BigDecimal(0.5));
			booking.setTicketPrice(discountedPrice);
		}
		
		// 20% Discount for the afternoon booking
		LocalTime now = LocalTime.now();
		if(!now.isBefore(AFTER_NOON) && now.isBefore(MID_NIGHT)) {
			BigDecimal discountedPrice = booking.getTicketPrice().multiply(new BigDecimal(0.2));
			booking.setTicketPrice(discountedPrice);
		}
		
		return booking;
	}
	
}
