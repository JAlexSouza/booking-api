package br.com.ntconsult.bookingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class BookingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApiApplication.class, args);
	}

}
