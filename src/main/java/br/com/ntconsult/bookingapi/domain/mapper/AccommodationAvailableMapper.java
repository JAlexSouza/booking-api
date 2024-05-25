package br.com.ntconsult.bookingapi.domain.mapper;

import br.com.ntconsult.bookingapi.domain.dto.AccommodationAvailableDTO;
import br.com.ntconsult.bookingapi.domain.entity.Accommodation;
import br.com.ntconsult.bookingapi.domain.entity.Address;
import br.com.ntconsult.bookingapi.domain.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class AccommodationAvailableMapper {

    public AccommodationAvailableDTO convertToAccommodationAvailableDTO(Accommodation accommodation, Hotel hotel, Address address) {
        return AccommodationAvailableDTO.builder()
                .accommodationId(accommodation.getId())
                .roomQuantity(accommodation.getRoomQuantity())
                .dailyRate(accommodation.getDailyRate())
                .hotelName(hotel.getName())
                .hotelAddress(address.getStreet() + ", " + address.getCity() + "-" + address.getState())
                .build();
    }
}
