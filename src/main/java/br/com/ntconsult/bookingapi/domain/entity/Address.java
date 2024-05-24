package br.com.ntconsult.bookingapi.domain.entity;

import br.com.ntconsult.bookingapi.domain.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
public class Address {

    @Id
    private Integer id;
    @Column
    private State state;
    @Column
    private String city;
    @Column
    private String street;
}
