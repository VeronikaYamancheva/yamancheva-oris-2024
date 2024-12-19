package ru.itis.vhsroni.repository;

import ru.itis.vhsroni.model.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findUserByEmail(String email);

    Optional<UserEntity> findUserByNickname(String nickname);

    Optional<UserEntity> saveNewUser(UserEntity user);

    void addAvatar(Long userId, UUID avatarId);

}