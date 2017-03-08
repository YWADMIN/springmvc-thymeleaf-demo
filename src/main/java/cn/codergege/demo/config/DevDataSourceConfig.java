package cn.codergege.demo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DevDataSourceConfig {

    // todo 读取 properties

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setInitialSize(5);
        ds.setMaxIdle(10);
        ds.setMaxTotal(20);

        return ds;
    }
}
