package ru.itis.vhsroni.service.impl;

import lombok.RequiredArgsConstructor;
import ru.itis.vhsroni.dto.ChatDto;
import ru.itis.vhsroni.dto.MessageDto;
import ru.itis.vhsroni.dto.MessageSendingDto;
import ru.itis.vhsroni.dto.UserDataResponse;
import ru.itis.vhsroni.mapper.ChatMapper;
import ru.itis.vhsroni.mapper.MessageMapper;
import ru.itis.vhsroni.mapper.UserMapper;
import ru.itis.vhsroni.model.MessageEntity;
import ru.itis.vhsroni.repository.ChatRepository;
import ru.itis.vhsroni.service.ChatsService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChatsServiceImpl implements ChatsService {

    private final ChatRepository chatRepository;

    private final ChatMapper chatMapper;
    private final UserMapper userMapper;
    private final MessageMapper messageMapper;

    @Override
    public Optional<ChatDto> findChatById(Long chatId) {
        return chatRepository.findById(chatId).map(chatMapper::toDto);
    }

    @Override
    public List<ChatDto> findAllChatsByUserId(Long userId) {
        return chatRepository.findAllByUserId(userId).stream()
                .map(chatMapper::toDto)
                .toList();
    }

    @Override
    public boolean isUserChat(Long userId, Long chatId) {
        return findAllUsersInChat(chatId).stream().anyMatch(c -> c.getId().equals(userId));
    }

    @Override
    public List<UserDataResponse> findAllUsersInChat(Long chatId) {
        return chatRepository.findAllUsersInChat(chatId).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<MessageDto> findAllMessagesInChat(Long chatId) {
        return chatRepository.findAllMessagesInChat(chatId).stream()
                .map(messageMapper::toDto)
                .toList();
    }

    @Override
    public MessageDto sendNewMessage(MessageSendingDto dto) {
        return messageMapper.toDto(chatRepository.saveNewMessage(MessageEntity.builder()
                .chatId(dto.getChatId())
                .authorId(dto.getUserId())
                .text(dto.getText())
                .build()));
    }
}
