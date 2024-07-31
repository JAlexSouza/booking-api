package br.com.ntconsult.bookingapi.service;

import br.com.ntconsult.bookingapi.config.exception.custom.AccommodationUnavailableException;
import br.com.ntconsult.bookingapi.domain.dto.AccommodationAvailableDTO;
import br.com.ntconsult.bookingapi.domain.entity.Address;
import br.com.ntconsult.bookingapi.domain.entity.Hotel;
import br.com.ntconsult.bookingapi.domain.mapper.AccommodationAvailableMapper;
import br.com.ntconsult.bookingapi.domain.record.AccommodationSearch;
import br.com.ntconsult.bookingapi.repository.AccommodationRepository;
import br.com.ntconsult.bookingapi.repository.AddressRepository;
import br.com.ntconsult.bookingapi.repository.HotelRepository;
import br.com.ntconsult.bookingapi.service.contracts.AccommodationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AccommodationAvailableMapper accommodationAvailableMapper;

    @Cacheable("accommodations")
    public Flux<AccommodationAvailableDTO> searchAccommodation(AccommodationSearch search){

        return accommodationRepository.findAccommodations(
                        search.state(),
                        search.city(),
                        search.roomQuantity(),
                        search.peopleAmount(),
                        search.checkInDate(),
                        search.checkOutDate()
                )
                .flatMap(accommodation ->
                        hotelRepository.findById(accommodation.getIdHotel())
                                .flatMap(hotel ->
                                        addressRepository.findById(hotel.getIdAddress())
                                                .map(address -> accommodationAvailableMapper.convertToAccommodationAvailableDTO(accommodation, hotel, address))
                                )
                )
                .switchIfEmpty(Mono.error(new AccommodationUnavailableException("Teste 02")))
                .doFirst(() -> log.info("Receiving searching data: {}", search))
                .doOnRequest( a -> log.info("Doing searching..."))
                .doAfterTerminate(() -> log.info("Searching finish."));

    }
}
