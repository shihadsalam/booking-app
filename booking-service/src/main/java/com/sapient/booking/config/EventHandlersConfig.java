package com.sapient.booking.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.model.SeatEvent;

@Configuration
public class EventHandlersConfig {

    @Autowired
    private BookingStatusUpdateEventHandler bookingEventHandler;

    @Bean
    public Consumer<SeatEvent> seatInventoryEventConsumer(){
        return ie -> {
        	bookingEventHandler.updateBooking(ie.getSeatsDTO().getBookingId(), po -> {
                po.setSeatStatus(ie.getSeatStatus());
            });
        };
    }

}
