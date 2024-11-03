package ru.vhsroni.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class CarEntity {

    private Long id;

    private String title;

}