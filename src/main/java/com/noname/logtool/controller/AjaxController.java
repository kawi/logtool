package com.noname.logtool.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noname.logtool.service.LogtoolService;
import com.sun.org.apache.commons.collections.ListUtils;
import com.sun.org.apache.commons.collections.SetUtils;

@Controller
public class AjaxController {

	@Autowired
	private LogtoolService logtoolService;
	
    @RequestMapping(value = "/checkPackage", method = RequestMethod.GET)
    public @ResponseBody
    String getPackage(@RequestParam String input) {
        Map<String, Logger> loggers = logtoolService.getLoggersMap();
        List<String> packs = new ArrayList<String>();

        for (String logger : loggers.keySet()) {
            if (logger.startsWith(input)) {
                packs.add(logger);
            }
        }
        if (packs.size() == 1) {
            return packs.get(0);
        }
        return "";
    }

   

    @RequestMapping(value = "/setLogLevel", method = RequestMethod.GET)
    public @ResponseBody
    String setLogLevel(@RequestParam String pack, @RequestParam String level, HttpServletResponse response)
            throws IOException {
        Map<String, Logger> loggers = logtoolService.getLoggersMap();
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