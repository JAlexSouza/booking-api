package br.com.ntconsult.bookingapi.service;

import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import br.com.ntconsult.bookingapi.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AccommodationServiceImpl {

    @Autowired
    private AccommodationRepository accommodationRepository;

    public Flux<Accommodation> getAccommodations(){
        return accommodationRepository.findAll();
    }
}
