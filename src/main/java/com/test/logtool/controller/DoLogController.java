package com.test.logtool.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.logtool.service.TestLogService;

@Controller
public class DoLogController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestLogService testLogService;

    @RequestMapping(value = "/doLog", method = RequestMethod.GET)
    public @ResponseBody
    String doLog(@RequestParam String level, HttpServletResponse response) throws IOException {
        log.error(String.format("Initiate logging with log set to: error %s, warn: %s, info %s, debug %s, trace %s",
                log.isErrorEnabled(), log.isWarnEnabled(), log.isInfoEnabled(), log.isDebugEnabled(),
                log.isTraceEnabled()));

        String msg = "Now I'm logging at level: %s";

        testLogService.testLog(level);

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
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "not ok");
            return "not ok";
        }
        return "ok";
    }

}
