package test.shoppingrest.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DbUtil {
    public static JdbcTemplate mySqlJdbcTemplate() {
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/test", "root", "password");
        ((DriverManagerDataSource) dataSource).setDriverClassName("com.mysql.jdbc.Driver");
        return new JdbcTemplate(dataSource);
    }
}
