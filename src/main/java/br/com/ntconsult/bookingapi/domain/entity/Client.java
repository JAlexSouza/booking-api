package br.com.ntconsult.bookingapi.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
public class Client {

    @Id
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
}
