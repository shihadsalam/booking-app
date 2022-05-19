package com.sapient.theatre.data;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.model.BookingEvent;
import com.sapient.model.BookingStatus;
import com.sapient.model.SeatEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class SeatInventoryConfig {

    @Autowired
    private SeatService service;

    @Bean
    public Function<Flux<BookingEvent>, Flux<SeatEvent>> seatInventoryProcessor() {
        return flux -> flux.flatMap(this::processSeatInventory);
    }

    private Mono<SeatEvent> processSeatInventory(BookingEvent event){
        if(event.getBookingStatus().equals(BookingStatus.ORDER_CREATED)){
            return Mono.fromSupplier(() -> this.service.newBookingInventory(event));
        }
        return Mono.fromRunnable(() -> this.service.cancelBookingInventory(event));
    }
}