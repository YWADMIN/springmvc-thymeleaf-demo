import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.util.FileSize

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.ERROR
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

scan("30 seconds")
appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} [%level] - %m%n"
  }
}
appender("TIME-ERROR-OUT", RollingFileAppender) {
  file = "logs/error.log"
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} [%class:%line] - %m%n"
  }
  filter(LevelFilter) {
    level = ERROR
    onMatch = ACCEPT
    onMismatch = DENY
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "logs/error.%d{yyyy-MM-dd}.log.zip"
    maxHistory = 30
    totalSizeCap = new FileSize(1 * FileSize.GB_COEFFICIENT) // 1GB
  }
}
appender("TIME-DEBUG-OUT", RollingFileAppender) {
  file = "logs/debug.log"
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} [%class:%line] - %m%n"
  }
  filter(LevelFilter) {
    level = DEBUG
    onMatch = ACCEPT
    onMismatch = DENY
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "logs/debug.%d{yyyy-MM-dd}.log.zip"
    maxHistory = 30
    totalSizeCap = new FileSize(1 * FileSize.GB_COEFFICIENT) // 1GB
  }
}
appender("TIME-INFO-OUT", RollingFileAppender) {
  file = "logs/info.log"
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} [%class:%line] - %m%n"
  }
  filter(LevelFilter) {
    level = INFO
    onMatch = ACCEPT
    onMismatch = DENY
  }
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "logs/info.%d{yyyy-MM-dd}.log.zip"
    maxHistory = 30
    totalSizeCap = new FileSize(1 * FileSize.GB_COEFFICIENT) // 1GB
  }
}
logger("org.apache.ibatis", DEBUG, ["STDOUT"])
logger("java.sql", DEBUG, ["STDOUT"])
root(DEBUG, ["STDOUT", "TIME-ERROR-OUT", "TIME-DEBUG-OUT", "TIME-INFO-OUT"])
			
			