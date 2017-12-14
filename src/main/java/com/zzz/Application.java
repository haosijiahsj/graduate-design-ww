package com.zzz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * app入口，启动端口在yml中指定
 * //@Configuration
 * //@EntityScan("com.zzz.model.po")
 * //@EnableJpaRepositories("com.zzz.dao")
 * //@EnableTransactionManagement
 * //@EnableAutoConfiguration
 * //@ImportResource("classpath:config/applicationContext.xml")
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource("classpath:applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("graduate-design-ww应用启动成功！");
    }

}
