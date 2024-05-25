package br.com.ntconsult.bookingapi.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccommodationAvailableDTO {

    private Integer accommodationId;
    private Integer roomQuantity;
    private BigDecimal dailyRate;
    private String hotelName;
    private String hotelAddress;
}
