package ru.itis.vhsroni.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {

    private Long id;

    private String text;

    private Long authorId;

    private Long chatId;
}
