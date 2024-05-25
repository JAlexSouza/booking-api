package br.com.ntconsult.bookingapi.service;

import br.com.ntconsult.bookingapi.domain.dto.AccommodationAvailableDTO;
import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import br.com.ntconsult.bookingapi.domain.entity.Address;
import br.com.ntconsult.bookingapi.domain.entity.Hotel;
import br.com.ntconsult.bookingapi.domain.enums.State;
import br.com.ntconsult.bookingapi.domain.mapper.AccommodationAvailableMapper;
import br.com.ntconsult.bookingapi.domain.record.AccommodationSearch;
import br.com.ntconsult.bookingapi.repository.AccommodationRepository;
import br.com.ntconsult.bookingapi.repository.AddressRepository;
import br.com.ntconsult.bookingapi.repository.HotelRepository;
import br.com.ntconsult.bookingapi.service.contracts.AccommodationService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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

    public Mono<ResponseEntity<List<AccommodationAvailableDTO>>> searchAccommodation(AccommodationSearch search){

        log.info("Receiving searching data: {}", search);
        List<AccommodationAvailableDTO> accommodationsDTO = new ArrayList<AccommodationAvailableDTO>();

        log.info("Consulting accommodations...");
        Flux<Accommodation> accommodationsFound = accommodationRepository.findAccommodations(
                search.state(),
                search.city(),
                search.roomQuantity(),
                search.maxCapacity(),
                search.checkInDate(),
                search.checkOutDate()
        );

        log.info("Verifying if there are accommodations...");
        if(Objects.isNull(accommodationsFound))
            return Mono.just(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        Mono<List<Accommodation>> accommodationListMono = accommodationsFound.collectList();
        List<Accommodation> accommodations = accommodationListMono.block();

        for(Accommodation accommodation : accommodations){
            Hotel hotel = hotelRepository.findById(accommodation.getIdHotel()).block();
            Address address = addressRepository.findById(hotel.getIdAddress()).block();

            AccommodationAvailableDTO accommodationAvailableDTO = accommodationAvailableMapper.convertToAccommodationAvailableDTO(accommodation, hotel, address);

            accommodationsDTO.add(accommodationAvailableDTO);
        }

        log.info("Returning accommodations");
        return Mono.just(new ResponseEntity<List<AccommodationAvailableDTO>>(accommodationsDTO, HttpStatus.OK));
    }
}
