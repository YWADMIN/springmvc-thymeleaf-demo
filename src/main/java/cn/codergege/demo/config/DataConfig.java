package cn.codergege.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import({DataSourceConfig.class})
public class DataConfig {

    // JdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource embeddedDataSource) {
        return new JdbcTemplate(embeddedDataSource);
    }

}
