package br.com.ntconsult.bookingapi.repository;

import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import br.com.ntconsult.bookingapi.domain.enums.State;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface AccommodationRepository extends ReactiveCrudRepository<Accommodation, Integer> {

    @Query( " select * "
            + " from Accommodation a "
            + " , Hotel hot "
            + " , Address addr"
            + " where hot.id_address = addr.id "
            + " and hot.id = a.id_hotel "
            + " and addr.state = :state "
            + " and addr.city = :city "
            + " and a.room_quantity = :roomQuantity "
            + " and a.max_capacity >= :peopleAmount  "
            + " and a.id not in ( select acc.id "
            + " from accommodation acc"
            + " , booking boo "
            + " where acc.id = boo.id_accommodation"
            + " and :checkInDate between boo.check_in_date AND  boo.check_out_date - INTERVAL '1 days' "
            + " or :checkOutDate - INTERVAL '1 days' between boo.check_in_date AND  boo.check_out_date "
            + " and boo.check_out_date not between :checkInDate and :checkOutDate )"
    )
    Flux<Accommodation> findAccommodations(
            State state,
            String city,
            Integer roomQuantity,
            Integer peopleAmount,
            LocalDate checkInDate,
            LocalDate checkOutDate
    );
}
