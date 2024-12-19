package ru.itis.vhsroni.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import ru.itis.vhsroni.dto.SignUpRequest;
import ru.itis.vhsroni.dto.UserDataResponse;
import ru.itis.vhsroni.mapper.UserMapper;
import ru.itis.vhsroni.model.UserEntity;
import ru.itis.vhsroni.util.AuthUtils;

@Slf4j
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(SignUpRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .hashPassword(AuthUtils.hashPassword(request.getPassword()))
                .build();
    }

    @Override
    public UserDataResponse toDto(UserEntity entity) {
        return UserDataResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .avatarId(entity.getAvatarId())
                .build();
    }
}
