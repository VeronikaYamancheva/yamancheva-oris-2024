package ru.itis.vhsroni.mapper;

import ru.itis.vhsroni.dto.MessageDto;
import ru.itis.vhsroni.model.MessageEntity;

public interface MessageMapper {

    MessageDto toDto(MessageEntity entity);
}
