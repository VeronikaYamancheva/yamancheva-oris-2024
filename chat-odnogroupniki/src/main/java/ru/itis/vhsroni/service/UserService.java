package ru.itis.vhsroni.service;

import ru.itis.vhsroni.dto.AuthResponse;
import ru.itis.vhsroni.dto.SignInRequest;
import ru.itis.vhsroni.dto.SignUpRequest;

import java.util.UUID;

public interface UserService {

    AuthResponse signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);

    void setAvatar(Long userId, UUID fileId);

}
