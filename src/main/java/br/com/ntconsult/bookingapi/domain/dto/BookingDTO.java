package br.com.ntconsult.bookingapi.domain.dto;

import br.com.ntconsult.bookingapi.domain.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BookingDTO {
    private Integer id;
    private BigDecimal totalValue;
    private LocalDate bookingDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean checkIn;
    private boolean checkOut;
    private BookingStatus status;
    private Integer idClient;
    private Integer idAccommodation;
}
