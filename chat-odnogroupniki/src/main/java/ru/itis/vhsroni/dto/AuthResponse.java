package ru.itis.vhsroni.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class AuthResponse {

    private int status;

    private String statusDesc;

    private UserDataResponse user;

}
