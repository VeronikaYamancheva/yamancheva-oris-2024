package ru.itis.vhsroni.mapper;

import ru.itis.vhsroni.dto.ChatDto;
import ru.itis.vhsroni.model.ChatEntity;

public interface ChatMapper {

    ChatDto toDto(ChatEntity entity);

}
