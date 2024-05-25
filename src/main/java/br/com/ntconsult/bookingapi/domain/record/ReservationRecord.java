package br.com.ntconsult.bookingapi.domain.record;

import java.time.LocalDate;

public record ReservationRecord(ClientRecord client, Integer idAccommodation, LocalDate checkInDate, LocalDate checkOutDate){
}
