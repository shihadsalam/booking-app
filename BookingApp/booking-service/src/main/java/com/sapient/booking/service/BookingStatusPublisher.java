package com.sapient.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.model.BookingDTO;
import com.sapient.model.BookingEvent;
import com.sapient.model.BookingStatus;

import reactor.core.publisher.Sinks;

@Service
public class BookingStatusPublisher {

    @Autowired
    private Sinks.Many<BookingEvent> bookingSink;

    public void raiseBookingEvent(final BookingDTO bookingOrder, BookingStatus orderStatus) {
        this.bookingSink.tryEmitNext(new BookingEvent(bookingOrder, orderStatus));
    }
	
}
