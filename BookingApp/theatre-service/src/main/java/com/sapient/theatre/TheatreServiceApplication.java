package com.sapient.theatre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

// Disabled kafka auto config for testing purpose. To include Kafka capability, set up kafka server, configure the server details in config and then remove the exclude attribute in auto config
@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@EnableScheduling
public class TheatreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheatreServiceApplication.class, args);
	}

}
