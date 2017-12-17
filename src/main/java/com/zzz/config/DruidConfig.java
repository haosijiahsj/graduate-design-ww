package com.zzz.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by 胡胜钧 on 12/16 0016.
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidConfig {

    private static final String DRUID_URL = "/druid/*";
    private static final String URL_PATTERNS = "/*";
    private static final String LOGIN_USERNAME = "root";
    private static final String LOGIN_PASSWORD = "123456";
    private static final String LOG_SLOW_SQL = "true";
    private static final String EXCLUSIONS = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";
    private static final String PROFILE_ENABLE = "true";

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        log.info("初始化druid连接池！");
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(DRUID_URL);
        reg.addInitParameter("loginUsername", LOGIN_USERNAME);
        reg.addInitParameter("loginPassword", LOGIN_PASSWORD);
        reg.addInitParameter("logSlowSql", LOG_SLOW_SQL);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(URL_PATTERNS);
        filterRegistrationBean.addInitParameter("exclusions", EXCLUSIONS);
        filterRegistrationBean.addInitParameter("profileEnable", PROFILE_ENABLE);
        return filterRegistrationBean;
    }

}