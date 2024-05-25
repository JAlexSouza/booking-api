package br.com.ntconsult.bookingapi.domain.mapper;

import br.com.ntconsult.bookingapi.domain.dto.BookingDTO;
import br.com.ntconsult.bookingapi.domain.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDTO bookingToBookingDTO(Booking booking){
        return BookingDTO.builder()
                .id(booking.getId())
                .totalValue(booking.getTotalValue())
                .bookingDate(booking.getBookingDate())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .checkIn(booking.isCheckIn())
                .checkOut(booking.isCheckOut())
                .status(booking.getStatus())
                .idClient(booking.getIdClient())
                .idAccommodation(booking.getIdAccommodation())
                .build();
    }
}
