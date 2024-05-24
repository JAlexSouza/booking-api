package br.com.ntconsult.bookingapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
public class Accommodation {

    @Id
    private Integer id;
    @Column
    private Integer roomQuantity;
    @Column
    private Integer maxCapacity;
    @Column
    private BigDecimal dailyRate;
    @Column
    private boolean isAvailable;
    @Column
    private Integer idHotel;
}
