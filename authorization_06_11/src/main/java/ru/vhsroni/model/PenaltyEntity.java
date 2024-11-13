package ru.vhsroni.model;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyEntity {

    private Long id;

    private Long car_id;

    private Integer amount;

    private Date daytime;

}
