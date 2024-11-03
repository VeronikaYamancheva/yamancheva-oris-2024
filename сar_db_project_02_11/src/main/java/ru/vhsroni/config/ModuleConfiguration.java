package ru.vhsroni.config;

import lombok.experimental.UtilityClass;
import ru.vhsroni.util.PropertyReader;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@UtilityClass
public class ModuleConfiguration {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());

    public static JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    private static DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(PropertyReader.getProperty("DB_URL"));
        dataSource.setUser(PropertyReader.getProperty("DB_USER"));
        dataSource.setPassword(PropertyReader.getProperty("DB_PASSWORD"));
        return dataSource;
    }
}
