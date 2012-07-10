package com.noname.logtool.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

    @RequestMapping(value = "/package", method = RequestMethod.GET)
    public @ResponseBody
    Set<String> getPackage(@RequestParam String input) {
        Map<String, Logger> loggers = getLoggersMap();
        Set<String> packs = new HashSet<String>();

        for (String logger : loggers.keySet()) {
            if (logger.startsWith(input)) {
                packs.add(logger);
            }
        }
        return packs;
    }

    private Map<String, Logger> loggersMap;

    private Map<String, Logger> getLoggersMap() {
        if (CollectionUtils.isEmpty(loggersMap)) {
            Enumeration<Logger> loggers = LogManager.getCurrentLoggers();
            loggersMap = new HashMap<String, Logger>();
            Logger rootLogger = LogManager.getRootLogger();
            if (!loggersMap.containsKey(rootLogger.getName())) {
                loggersMap.put(rootLogger.getName(), rootLogger);
            }
            while (loggers.hasMoreElements()) {
                Logger logger = loggers.nextElement();
                if (!loggersMap.containsKey(logger.getName())) {
                    loggersMap.put(logger.getName(), logger);
                }
            }
        }
        return loggersMap;
    }

    @RequestMapping(value = "/setLogLevel", method = RequestMethod.GET)
    public @ResponseBody
    String setLogLevel(@RequestParam String pack, @RequestParam String level, HttpServletResponse response)
            throws IOException {
        Map<String, Logger> loggers = getLoggersMap();
        Logger selectedLogger = loggers.get(pack);
        if (selectedLogger != null) {
            selectedLogger.setLevel(Level.toLevel(level));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "not ok");
            return "not ok";
        }
        return "ok";
    }
}