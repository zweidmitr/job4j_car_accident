package ru.job4j.accident.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:app.properties") /* - эта аннотация говорит Spring считать файл.
Далее настройки можно получить через аннотацию @Value. */
@EnableTransactionManagement
public class JdbcConfig {
    /**
     * Метод ds загружает пул соединений.
     */
    @Bean
    public DataSource ds(@Value("${jdbc.driver}") String driver,
                         @Value("${jdbc.url}") String url,
                         @Value("${jdbc.username}") String username,
                         @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    /**
     * Метод jdbc создает обертку для работы с базой.
     */
    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
