package br.com.ntconsult.bookingapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
public class Hotel {

    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer idAddress;
}
