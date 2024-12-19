package ru.itis.vhsroni.mapper.impl;

import ru.itis.vhsroni.dto.ChatDto;
import ru.itis.vhsroni.mapper.ChatMapper;
import ru.itis.vhsroni.model.ChatEntity;

public class ChatMapperImpl implements ChatMapper {
    @Override
    public ChatDto toDto(ChatEntity entity) {
        return ChatDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }
}
