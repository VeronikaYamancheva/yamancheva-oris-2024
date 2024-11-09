package ru.vhsroni.util;

import lombok.experimental.UtilityClass;
import ru.vhsroni.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertyReader {
    private final Properties properties;
    private final Properties sqlQuires;

    static {
        properties = new Properties();
        sqlQuires = new Properties();
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("app.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Having problems reading the property-file `app`", e);
        }
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("sql_queries.properties");
            sqlQuires.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Having problems reading the property-file `sql_queries`", e);
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public static String getSqlQuery(String propertyName) {return sqlQuires.getProperty(propertyName);}
}
