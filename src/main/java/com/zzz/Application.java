package com.zzz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * app入口，启动端口在yml中指定
 * 使用 --spring.profiles.active=pro指定生产环境配置文件
 * 使用 --server.port=8089指定端口号
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource("classpath:config/applicationContext.xml")
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

        log.info("Application Startup Success !");
    }

}
