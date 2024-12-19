package ru.itis.vhsroni.repository;

import ru.itis.vhsroni.model.ChatEntity;
import ru.itis.vhsroni.model.MessageEntity;
import ru.itis.vhsroni.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    Optional<ChatEntity> findById(Long chatId);

    List<ChatEntity> findAllByUserId(Long userId);

    List<UserEntity> findAllUsersInChat(Long chatId);

    List<MessageEntity> findAllMessagesInChat(Long chatId);

    MessageEntity saveNewMessage(MessageEntity message);

}
