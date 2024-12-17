package ru.itis.vhsroni.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessage {

    private String userName;

    private Long chatId;

    private String message;

    private String authorAvatarId;

}
