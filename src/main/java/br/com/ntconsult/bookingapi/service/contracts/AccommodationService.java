package br.com.ntconsult.bookingapi.service.contracts;

import br.com.ntconsult.bookingapi.domain.dto.AccommodationAvailableDTO;
import br.com.ntconsult.bookingapi.domain.record.AccommodationSearch;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AccommodationService {

    Flux<AccommodationAvailableDTO> searchAccommodation(AccommodationSearch search);
}
