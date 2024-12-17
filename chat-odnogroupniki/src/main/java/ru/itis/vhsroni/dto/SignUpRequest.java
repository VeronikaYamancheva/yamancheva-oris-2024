package ru.itis.vhsroni.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String email;

    private String password;

    private String nickname;

}
