package br.com.ntconsult.bookingapi.service.contracts;

import br.com.ntconsult.bookingapi.domain.dto.BookingDTO;
import br.com.ntconsult.bookingapi.domain.record.ReservationRecord;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BookingService {

    Mono<ResponseEntity<BookingDTO>> makeReservation(ReservationRecord reservation) throws Exception;
}
