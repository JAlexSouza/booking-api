package br.com.ntconsult.bookingapi.domain.record;

import br.com.ntconsult.bookingapi.domain.enums.State;

import java.time.LocalDate;

public record AccommodationSearch(State state,
                                  String city,
                                  Integer roomQuantity,
                                  Integer maxCapacity,
                                  LocalDate checkInDate,
                                  LocalDate checkOutDate
                                  ) {}