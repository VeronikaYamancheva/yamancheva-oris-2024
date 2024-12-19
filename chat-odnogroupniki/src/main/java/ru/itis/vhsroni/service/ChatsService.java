package ru.itis.vhsroni.service;

import ru.itis.vhsroni.dto.ChatDto;
import ru.itis.vhsroni.dto.MessageDto;
import ru.itis.vhsroni.dto.MessageSendingDto;
import ru.itis.vhsroni.dto.UserDataResponse;

import java.util.List;
import java.util.Optional;

public interface ChatsService {

    Optional<ChatDto> findChatById(Long chatId);

    List<ChatDto> findAllChatsByUserId(Long userId);

    boolean isUserChat(Long userId, Long chatId);

    List<UserDataResponse> findAllUsersInChat(Long chatId);

    List<MessageDto> findAllMessagesInChat(Long chatId);

    MessageDto sendNewMessage(MessageSendingDto dto);

}
