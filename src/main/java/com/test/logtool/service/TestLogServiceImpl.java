package com.test.logtool.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestLogServiceImpl implements TestLogService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void testLog(String level) throws IOException {

        log.error(String.format("Initiate logging with log set to: error %s, warn: %s, info %s, debug %s, trace %s",
                log.isErrorEnabled(), log.isWarnEnabled(), log.isInfoEnabled(), log.isDebugEnabled(),
                log.isTraceEnabled()));

        String msg = "Now I'm logging at level: %s";

        if (level.equalsIgnoreCase("error")) {
            log.error(String.format(msg, level));
        } else if (level.equalsIgnoreCase("warn")) {
            log.warn(String.format(msg, level));
        } else if (level.equalsIgnoreCase("info")) {
            log.info(String.format(msg, level));
        } else if (level.equalsIgnoreCase("debug")) {
            log.debug(String.format(msg, level));
        } else if (level.equalsIgnoreCase("trace")) {
            log.trace(String.format(msg, level));
        }
    }
}
