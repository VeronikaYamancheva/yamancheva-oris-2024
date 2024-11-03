package ru.vhsroni.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.vhsroni.config.ModuleConfiguration;
import ru.vhsroni.model.CarEntity;
import ru.vhsroni.service.CarService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CarServiceImpl implements CarService {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();
    private static final String SQL_SELECT_ALL = "SELECT * FROM car;";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM car WHERE id = ?;";
    private static final String SQL_SELECT_BY_TITLE = "SELECT * FROM car WHERE title = ?;";
    private static final String SQL_INSERT = "INSERT INTO car(title) VALUES (?);";
    private static final String SQL_DELETE = "DELETE FROM car WHERE id = ?;";
    private static final String SQL_UPDATE = "UPDATE car SET title = ? WHERE id = ?;";

    private final CarRowMapper carRowMapper = new CarRowMapper();

    @Override
    public List<CarEntity> findAll() {
        log.info("We are finding all cars in the db.");
        return jdbcTemplate.query(SQL_SELECT_ALL, carRowMapper);
    }

    @Override
    public CarEntity findCarById(Long id) {
        log.info("We are finding car with id = {}.", id);
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, carRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn("There is no car with id = {}", id);
            return null;
        }
    }

    @Override
    public CarEntity findCarByTitle(String title) {
        log.info("We are finding car with title = {}.", title);
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_BY_TITLE, new Object[]{title}, carRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn("There is no car with title = {}.", title);
            return null;
        }
    }

    @Override
    public CarEntity saveNewCar(CarEntity car) {
        log.info("We are saving a new car to the db.");
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"id"});
            ps.setString(1, car.getTitle());
            return ps;
        }, holder);
        Long id = Objects.requireNonNull(holder.getKey()).longValue();
        return findCarById(id);
    }

    @Override
    public boolean deleteCarById(Long id) {
        log.info("We are deleting car with id = {} from the db.", id);
        return jdbcTemplate.update(SQL_DELETE, id) == 1;
    }

    @Override
    public CarEntity updateCarById(CarEntity car, Long id) {
        log.info("We are updating car with id = {}.", id);
        int n = jdbcTemplate.update(SQL_UPDATE, car.getTitle(), id);
        if (n == 0) {
            log.warn("There is no car with id = {}.", id);
            return null;
        }
        return findCarById(id);

    }

    private static final class CarRowMapper implements RowMapper<CarEntity> {

        @Override
        public CarEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return CarEntity.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .build();
        }
    }
}
