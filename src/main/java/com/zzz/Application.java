package com.zzz;

import com.zzz.utils.CommandLineUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ImportResource;

/**
 * app入口，启动端口在yml中指定
 * 打包jar后启动可以使用 -p 8089 或者 --port 8089 来指定启动端口
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource("classpath:config/applicationContext.xml")
public class Application implements EmbeddedServletContainerCustomizer {

    private static Integer port;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (port != null) {
            log.info("use user defined port: [{}]", port);
            container.setPort(port);
        }
    }

    public static void main(String[] args) {
        port = CommandLineUtils.processCliArgsForPort(args);

        new SpringApplicationBuilder()
                .sources(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

        log.info("Application Startup Success !");
    }

}
