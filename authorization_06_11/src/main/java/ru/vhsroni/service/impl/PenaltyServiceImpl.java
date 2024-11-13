package ru.vhsroni.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.vhsroni.config.ModuleConfiguration;
import ru.vhsroni.model.PenaltyEntity;
import ru.vhsroni.service.PenaltyService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.vhsroni.util.PropertyReader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PenaltyServiceImpl implements PenaltyService {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private final PenaltyRowMapper penaltyRowMapper = new PenaltyRowMapper();

    @Override
    public List<PenaltyEntity> findAllPenaltiesByCarId(Long id) {
        log.info("We are finding all penalties by car with id = {}.", id);
        return jdbcTemplate.query(PropertyReader.getSqlQuery("SQL_SELECT_PENALTY_BY_CAR_ID"),
                new Object[] {id}, penaltyRowMapper);
    }

    @Override
    public List<PenaltyEntity> findAllPenaltiesWhereAmountLargerThan(Integer minValue) {
        log.info("We are finding all penalties where amount is larger than {}.", minValue);
        return jdbcTemplate.query(PropertyReader.getSqlQuery("SQL_SELECT_PENALTY_BY_AMOUNT"),
                new Object[]{minValue}, penaltyRowMapper);
    }

    @Override
    public List<PenaltyEntity> findAllPenaltiesOlderThanDate(Date minData) {
        log.info("We are finding all penalties which are older than {}.", minData);
        return jdbcTemplate.query(PropertyReader.getSqlQuery("SQL_SELECT_PENALTY_BY_DATE"),
                new Object[]{minData}, penaltyRowMapper);
    }

    @Override
    public PenaltyEntity addNewPenalty(PenaltyEntity penalty) {
        log.info("We are saving new penalty into db.");
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(PropertyReader.getSqlQuery("SQL_INSERT_PENALTY"),
                    new String[]{"id"});
            ps.setLong(1, penalty.getCar_id());
            ps.setInt(2, penalty.getAmount());
            ps.setDate(3, (java.sql.Date) penalty.getDaytime());
            return ps;
        }, holder);
        Long id = Objects.requireNonNull(holder.getKey().longValue());
        return findPenaltyById(id);
    }

    public PenaltyEntity findPenaltyById(Long id) {
        try {
            return jdbcTemplate.queryForObject(PropertyReader.getSqlQuery("SQL_SELECT_PENALTY_BY_ID"),
                    new Object[]{id}, penaltyRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.warn("There is no penalty with id = {}.", id);
            return null;
        }
    }

    private static final class PenaltyRowMapper implements RowMapper<PenaltyEntity> {
        @Override
        public PenaltyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            return PenaltyEntity.builder()
                    .id(rs.getLong("id"))
                    .car_id(rs.getLong("car_id"))
                    .amount(rs.getInt("amount"))
                    .daytime(rs.getDate("daytime"))
                    .build();
        }
    }
}
