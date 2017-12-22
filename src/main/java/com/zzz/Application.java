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
public class Application implements EmbeddedServletContainerCustomizer {

    private static Integer port;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (port != null) {
            container.setPort(port);
        }
    }

    public static void main(String[] args) {
        port = CommandLineUtils.processCliArgsForPort(args);

        new SpringApplicationBuilder()
                .sources(Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

        log.info("[ graduate-design-ww ] Application Startup Success !");
    }

}
