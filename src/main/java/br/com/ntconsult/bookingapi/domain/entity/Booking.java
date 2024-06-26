package br.com.ntconsult.bookingapi.domain.entity;

import br.com.ntconsult.bookingapi.domain.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
public class Booking {

    @Id
    private Integer id;
    @Column
    private BigDecimal totalValue;
    @Column
    private LocalDate bookingDate;
    @Column
    private LocalDate checkInDate;
    @Column
    private LocalDate checkOutDate;
    @Column
    private boolean checkIn;
    @Column
    private boolean checkOut;
    @Column
    private BookingStatus status;
    @Column
    private Integer idClient;
    @Column
    private Integer idAccommodation;
}
