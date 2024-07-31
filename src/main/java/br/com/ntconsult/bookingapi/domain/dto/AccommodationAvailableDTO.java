package br.com.ntconsult.bookingapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AccommodationAvailableDTO implements Serializable {

    private Integer accommodationId;
    private Integer roomQuantity;
    private Integer maxCapacity;
    private BigDecimal dailyRate;
    private String hotelName;
    private String hotelAddress;
}
