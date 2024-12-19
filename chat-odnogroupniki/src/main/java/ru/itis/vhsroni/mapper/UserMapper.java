package ru.itis.vhsroni.mapper;

import ru.itis.vhsroni.dto.SignUpRequest;
import ru.itis.vhsroni.dto.UserDataResponse;
import ru.itis.vhsroni.model.UserEntity;

public interface UserMapper {

    UserEntity toEntity(SignUpRequest request);

    UserDataResponse toDto(UserEntity entity);

}
