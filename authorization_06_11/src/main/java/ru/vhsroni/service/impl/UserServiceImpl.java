package ru.vhsroni.service.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.vhsroni.config.ModuleConfiguration;
import ru.vhsroni.dto.AuthResponse;
import ru.vhsroni.dto.SignInRequest;
import ru.vhsroni.dto.SignUpRequest;
import ru.vhsroni.model.UserEntity;
import ru.vhsroni.service.UserService;
import ru.vhsroni.util.AuthUtils;
import ru.vhsroni.util.PropertyReader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final UserRowMapper userRowMapper = new UserRowMapper();

    @Override
    public UserEntity findUserById(Long id) {
        try {
            return jdbcTemplate.queryForObject(PropertyReader.getSqlQuery("SQL_SELECT_USER_BY_ID"),
                    new Object[] {id}, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(PropertyReader.getSqlQuery("SQL_SELECT_USER_BY_EMAIL"),
                    new Object[] {email}, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByNickname(String nickname) {
        try {
            return jdbcTemplate.queryForObject(PropertyReader.getSqlQuery("SQL_SELECT_USER_BY_NICKNAME"),
                    new Object[] {nickname}, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public AuthResponse signUp(SignUpRequest request) {
        if(request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(request.getNickname() == null || request.getNickname().isBlank())
            return response(3, "Empty nickname", null);

        if(!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);

        if(!AuthUtils.checkPassword(request.getPassword()))
            return response(5, "Weak password", null);

        if(findUserByEmail(request.getEmail()) != null)
            return response(6, "Email taken", null);

        if(findUserByNickname(request.getNickname()) != null)
            return response(7, "Nickname taken", null);

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(PropertyReader.getSqlQuery("SQL_INSERT_USER"),
                    new String[] {"id"});
            ps.setString(1, request.getEmail());
            ps.setString(2, AuthUtils.hashPassword(request.getPassword()));
            ps.setString(3, request.getNickname());
            ps.setString(4, UUID.randomUUID().toString());
            return ps;
        }, holder);
        Long id = Objects.requireNonNull(holder.getKey().longValue());
        return response(0, "OK", findUserById(id));
    }

    @Override
    public AuthResponse signIn(SignInRequest request) {
        if(request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);
        UserEntity user = findUserByEmail(request.getEmail());

        if(user == null) return response(8, "Email not find", null);

        if(!AuthUtils.verifyPassword(request.getPassword(), user.getHashPassword()))
            return response(9, "Password mismatch", null);

        jdbcTemplate.update(PropertyReader.getSqlQuery("SQL_UPDATE_USER_TOKEN"),
                UUID.randomUUID().toString(), user.getId());

        return response(0, "OK", findUserByEmail(request.getEmail()));

    }

    @Override
    public AuthResponse signInByToken(String token) {
        try {
            UserEntity user = jdbcTemplate.queryForObject(PropertyReader.getSqlQuery(
                    "SQL_SELECT_USER_BY_TOKEN"), new Object[]{token}, userRowMapper);
            if (user == null) return response(10, "Token not found", null);
            if (user.getToken() == null || user.getTokenUsage() < 1)
                return response(11, "Token expired", null);

            jdbcTemplate.update(PropertyReader.getSqlQuery("SQL_UPDATE_USER"), user.getTokenUsage() - 1,
                    user.getId());
            return response(0, "OK", findUserById(user.getId()));
        } catch (EmptyResultDataAccessException e) {
            return response(10, "Token not found", null);
        }
    }

    private AuthResponse response(int status, String statusDesc, UserEntity user) {
        return AuthResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .user(user)
                .build();
    }

    private static final class UserRowMapper implements RowMapper<UserEntity> {
        @Override
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UserEntity.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .nickname(rs.getString("nickname"))
                    .hashPassword(rs.getString("hash_password"))
                    .token(rs.getString("token"))
                    .tokenUsage(rs.getInt("token_usage"))
                    .build();
        }
    }
}
