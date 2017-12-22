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
public class CommandLineUtils {

    public static Integer processCliArgsForPort(String[] args) {
        Options options = new Options();
        Option helpOption = Option.builder("h").longOpt("help").hasArg(false).desc("显示本帮助").build();
        Option portOption = Option.builder("p").longOpt("port").hasArg(true).desc("端口号").build();

        options.addOption(helpOption).addOption(portOption);

        CommandLineParser commandLineParser = new DefaultParser();
        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption(helpOption.getOpt())) {
                new HelpFormatter().printHelp("java -jar graduate-design-1.0-RELEASE.jar", options);
                System.exit(0);
            }
            if (!commandLine.hasOption(portOption.getOpt())) {
                log.info("no command -p or --port input ! use default port");
                return null;
            }

            String portStr = commandLine.getOptionValue(portOption.getOpt());
            return Integer.parseInt(portStr);
        } catch (ParseException | NumberFormatException e) {
            log.info("parse args error, use default port ! cause: {}", e.getMessage());
            return null;
        }
    }

}
