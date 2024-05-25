package br.com.ntconsult.bookingapi.service;

import br.com.ntconsult.bookingapi.config.exception.custom.AccommodationUnavailableException;
import br.com.ntconsult.bookingapi.config.exception.model.ExceptionMessage;
import br.com.ntconsult.bookingapi.domain.dto.BookingDTO;
import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import br.com.ntconsult.bookingapi.domain.entity.Booking;
import br.com.ntconsult.bookingapi.domain.entity.Client;
import br.com.ntconsult.bookingapi.domain.enums.BookingStatus;
import br.com.ntconsult.bookingapi.domain.mapper.BookingMapper;
import br.com.ntconsult.bookingapi.domain.record.ReservationRecord;
import br.com.ntconsult.bookingapi.repository.AccommodationRepository;
import br.com.ntconsult.bookingapi.repository.BookingRepository;
import br.com.ntconsult.bookingapi.repository.ClientRepository;
import br.com.ntconsult.bookingapi.service.contracts.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccommodationRepository accommodationRepository;
    @Autowired
    private BookingMapper bookingMapper;

    public Mono<ResponseEntity<BookingDTO>> makeReservation(ReservationRecord reservation) throws Exception{
        Client client;

        try {
            log.info("Receiving reservation: {}", reservation);
            Flux<Booking> bookingInPeriod = bookingRepository.findBookingInPeriod(reservation.idAccommodation(), reservation.checkInDate(), reservation.checkOutDate());

            log.info("Verifying if accommodation is available in period");
            if (!Objects.isNull(bookingInPeriod.blockFirst())){
                log.info("Accommodation is unavailable in period");
                throw new RuntimeException();
            }

            Mono<Client> clientFound = clientRepository.findByEmail(reservation.client().email());

            client = clientFound.block();

            log.info("Verifying if client exist already");
            if(Objects.isNull(client)){
                log.info("Creating a new client");
                client = Client.builder()
                        .firstName(reservation.client().firstName())
                        .lastName(reservation.client().lastName())
                        .email(reservation.client().email())
                        .build();

                client= clientRepository.save(client).block();
            }

            Accommodation accommodation = accommodationRepository.findById(reservation.idAccommodation()).block();

            log.info("Creating a new booking");
            Booking booking = Booking.builder()
                    .totalValue(accommodation.getDailyRate().multiply(BigDecimal.valueOf(DAYS.between(reservation.checkInDate(), reservation.checkOutDate()))))
                    .bookingDate(LocalDate.now())
                    .checkInDate(reservation.checkInDate())
                    .checkOutDate(reservation.checkOutDate())
                    .checkIn(false)
                    .checkOut(false)
                    .status(BookingStatus.ACTIVE)
                    .idAccommodation(reservation.idAccommodation())
                    .idClient(client.getId())
                    .build();

            booking = bookingRepository.save(booking).block();

            BookingDTO bookingDTO = bookingMapper.bookingToBookingDTO(booking);

            log.info("Returning booking");
            return Mono.just(new ResponseEntity<BookingDTO>(bookingDTO, HttpStatus.CREATED));
        } catch (RuntimeException ex) {
            throw new AccommodationUnavailableException(ExceptionMessage.EXCEPTION_MESSAGE_400_ACCOMMODATION_UNAVAILABLE);
        }
    }
}
