package com.zzz.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

/**
 * Created by on 2017/12/22.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionsUtils {

    public static Integer processCliArgsForPort(String[] args) {
        Options options = new Options();
        Option portOption = Option.builder("p")
                .longOpt("port")
                .type(Integer.class)
                .hasArg(false)
                .build();
        options.addOption(portOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            if (!commandLine.hasOption(portOption.getOpt())) {
                log.info("no command -p or --port input ! use default port");
                return null;
            }

            String portStr = commandLine.getOptionValue(portOption.getOpt());
            return Integer.parseInt(portStr);
        } catch (ParseException | NumberFormatException e) {
            log.info("parse args error ! cause: {}", e.getMessage());
            return null;
        }
    }

}
