package br.com.ntconsult.bookingapi.repository;

import br.com.ntconsult.bookingapi.domain.entity.Booking;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingRepository extends ReactiveCrudRepository<Booking, Integer> {

    @Query(  " select * "
            + " from booking boo "
            + " , accommodation acc "
            + " where acc.id = boo.id_accommodation "
            + " and boo.id_accommodation = :idAccommodation "
            + " and :checkInDate between boo.check_in_date AND  boo.check_out_date - INTERVAL '1 days' "
            + " or :checkOutDate - INTERVAL '1 days' between boo.check_in_date AND  boo.check_out_date "
            + " and boo.check_out_date not between :checkInDate and :checkOutDate " )
    Flux<Booking> findBookingInPeriod(Integer idAccommodation,
                                      LocalDate checkInDate,
                                      LocalDate checkOutDate);
}
