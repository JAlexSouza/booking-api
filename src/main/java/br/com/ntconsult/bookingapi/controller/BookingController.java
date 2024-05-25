package br.com.ntconsult.bookingapi.controller;

import br.com.ntconsult.bookingapi.domain.dto.AccommodationAvailableDTO;
import br.com.ntconsult.bookingapi.domain.dto.BookingDTO;
import br.com.ntconsult.bookingapi.domain.record.AccommodationSearch;
import br.com.ntconsult.bookingapi.domain.record.ReservationRecord;
import br.com.ntconsult.bookingapi.service.AccommodationServiceImpl;
import br.com.ntconsult.bookingapi.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/booking-api/v1/booking")
public class BookingController {

    @Autowired
    private AccommodationServiceImpl accommodationService;
    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping
    public Mono<ResponseEntity<List<AccommodationAvailableDTO>>> searchAccommodation(@RequestBody AccommodationSearch search){
        return accommodationService.searchAccommodation(search);
    }

    @PostMapping
    public Mono<ResponseEntity<BookingDTO>> makeReservation(@RequestBody ReservationRecord reservation) throws Exception {
        return bookingService.makeReservation(reservation);
    }

}
