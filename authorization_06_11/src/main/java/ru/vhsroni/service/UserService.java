package ru.vhsroni.service;

import ru.vhsroni.dto.AuthResponse;
import ru.vhsroni.dto.SignInRequest;
import ru.vhsroni.dto.SignUpRequest;
import ru.vhsroni.model.UserEntity;

public interface UserService {

    UserEntity findUserById(Long id);

    UserEntity findUserByEmail(String email);

    UserEntity findUserByNickname(String nickname);

    AuthResponse signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);

    AuthResponse signInByToken(String token);
}
