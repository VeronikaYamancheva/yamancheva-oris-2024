package ru.itis.vhsroni.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponse {

    private Long id;

    private String email;

    private String nickname;

    private String avatarId;

}
