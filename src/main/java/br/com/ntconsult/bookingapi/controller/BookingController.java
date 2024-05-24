package br.com.ntconsult.bookingapi.controller;

import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import br.com.ntconsult.bookingapi.domain.entity.Address;
import br.com.ntconsult.bookingapi.domain.entity.Hotel;
import br.com.ntconsult.bookingapi.service.AccommodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private AccommodationServiceImpl accommodationService;

    @GetMapping
    public Flux<Accommodation> getAccommodation(){
        return accommodationService.getAccommodations();
    }
}
