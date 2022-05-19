package com.sapient.booking.config;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.model.BookingEvent;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class BookingConfig {

    @Bean
    public Sinks.Many<BookingEvent> orderSink(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<BookingEvent>> bookingSupplier(Sinks.Many<BookingEvent> sink){
        return sink::asFlux;
    }

}
