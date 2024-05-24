package br.com.ntconsult.bookingapi.repository;

import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccommodationRepository extends ReactiveCrudRepository<Accommodation, UUID> {
}
