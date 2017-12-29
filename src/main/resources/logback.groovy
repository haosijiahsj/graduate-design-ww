import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.util.FileSize

import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.ALL
import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.WARN

scan("60 seconds")
def appName = "graduate-design"

appender("FILE", RollingFileAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%date{yyyy-MM-dd HH:mm:ss.SSS} -%5level [%15.15thread] %-40.40logger{39} : %msg%n"
    charset = Charset.forName("UTF-8")
  }
  rollingPolicy(SizeAndTimeBasedRollingPolicy) {
    fileNamePattern = "../logs/${appName}-%d{yyyy-MM-dd}.%i.log"
    maxHistory = 30
    maxFileSize = FileSize.valueOf("64MB")
  }
  filter(ThresholdFilter) {
    level = DEBUG
  }
  prudent = true
}

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%date{yyyy-MM-dd HH:mm:ss.SSS} -%5level [%15.15thread] %-40.40logger{39} : %msg%n"
    charset = Charset.forName("UTF-8")
  }
  filter(ThresholdFilter) {
    level = WARN
  }
}

appender("CONSOLE", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%date{yyyy-MM-dd HH:mm:ss.SSS} -%5level [%15.15thread] %-40.40logger{39} : %msg%n"
    charset = Charset.forName("UTF-8")
  }
}

root(INFO, ["FILE", "CONSOLE"])
logger("pedestal", ALL)
logger("hammock-cafe", ALL)
logger("user", ALL)