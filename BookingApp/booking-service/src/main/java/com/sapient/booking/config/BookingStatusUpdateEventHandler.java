package com.sapient.booking.config;

import java.util.Objects;
import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.booking.data.Booking;
import com.sapient.booking.service.BookingRepository;
import com.sapient.booking.service.BookingStatusPublisher;
import com.sapient.model.BookingDTO;
import com.sapient.model.BookingStatus;
import com.sapient.model.SeatStatus;

@Service
public class BookingStatusUpdateEventHandler {

	@Autowired
	private BookingRepository repository;

	@Autowired
	private BookingStatusPublisher publisher;

	@Transactional
	public void updateBooking(final Long id, Consumer<Booking> consumer) {
		this.repository.findById(id).ifPresent(consumer.andThen(this::updateBooking));

	}

	private void updateBooking(Booking booking) {
		if (Objects.isNull(booking.getSeatStatus()))
			return;
		boolean isComplete = SeatStatus.RESERVED.equals(booking.getSeatStatus());
		BookingStatus orderStatus = isComplete ? BookingStatus.ORDER_COMPLETED : BookingStatus.ORDER_CANCELLED;
		BookingDTO bookingDTO = getBookingDTO(booking);
		bookingDTO.setBookingStatus(orderStatus);
		if (!isComplete) {
			this.publisher.raiseBookingEvent(bookingDTO, orderStatus);
		}
	}

	private BookingDTO getBookingDTO(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		BeanUtils.copyProperties(booking, bookingDTO);
		return bookingDTO;
	}

}