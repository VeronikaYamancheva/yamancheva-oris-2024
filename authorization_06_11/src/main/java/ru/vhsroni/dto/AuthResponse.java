package ru.vhsroni.dto;


import lombok.*;
import ru.vhsroni.model.UserEntity;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private int status;

    private String statusDesc;

    private UserEntity user;

}
