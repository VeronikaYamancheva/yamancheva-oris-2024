package ru.vhsroni.service.impl;

import lombok.extern.slf4j.Slf4j;
import ru.vhsroni.config.ModuleConfiguration;
import ru.vhsroni.model.PenaltyEntity;
import ru.vhsroni.service.PenaltyService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Slf4j
public class PenaltyServiceImpl implements PenaltyService {

    private final JdbcTemplate jdbcTemplate = ModuleConfiguration.jdbcTemplate();

    private static final String SQL_SELECT_BY_CAR_ID = "SELECT * FROM penalty WHERE car_id = ?;";
    private static final String SQL_SELECT_BY_DATE = "SELECT * FROM penalty WHERE daytime < ?;";
    private static final String SQL_SELECT_BY_AMOUNT = "SELECT * FROM penalty WHERE amount > ? ORDER BY amount;";

    private final PenaltyRowMapper penaltyRowMapper = new PenaltyRowMapper();

    @Override
    public List<PenaltyEntity> findAllByCarId(Long id) {
        log.info("We are finding all penalties by car with id = {}.", id);
        return jdbcTemplate.query(SQL_SELECT_BY_CAR_ID, new Object[] {id}, penaltyRowMapper);
    }

    @Override
    public List<PenaltyEntity> findAllWhereAmountLargerThan(int minValue) {
        log.info("We are finding all penalties where amount is larger than {}.", minValue);
        return jdbcTemplate.query(SQL_SELECT_BY_AMOUNT, new Object[]{minValue}, penaltyRowMapper);
    }

    @Override
    public List<PenaltyEntity> findAllOlderThanDate(Date minData) {
        log.info("We are finding all penalties which are older than {}.", minData);
        return jdbcTemplate.query(SQL_SELECT_BY_DATE, new Object[]{minData}, penaltyRowMapper);
    }

    @Override
    public PenaltyEntity addNewPenalty(PenaltyEntity penalty) {
        return null;
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
