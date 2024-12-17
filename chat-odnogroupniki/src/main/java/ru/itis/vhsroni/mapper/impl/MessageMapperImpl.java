package ru.itis.vhsroni.mapper.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.vhsroni.dto.MessageDto;
import ru.itis.vhsroni.mapper.MessageMapper;
import ru.itis.vhsroni.model.MessageEntity;
import ru.itis.vhsroni.model.UserEntity;
import ru.itis.vhsroni.repository.UserRepository;

@RequiredArgsConstructor
public class MessageMapperImpl implements MessageMapper {

    private final UserRepository userRepository;

    @Override
    public MessageDto toDto(MessageEntity entity) {
        UserEntity user = userRepository.findUserById(entity.getAuthorId()).get();
        return MessageDto.builder()
                .text(entity.getText())
                .authorNickname(user.getNickname())
                .authorAvatarId(user.getAvatarId())
                .build();
    }
}
