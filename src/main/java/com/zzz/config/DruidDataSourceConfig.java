package com.zzz.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by  on 2017/9/15.
 */
@Configuration
public class DruidDataSourceConfig {

    @Primary
    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(Properties properties) throws Exception {
        return DruidDataSourceFactory.createDataSource(properties);
    }

}
