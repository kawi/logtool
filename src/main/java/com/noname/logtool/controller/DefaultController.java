package com.noname.logtool.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noname.logtool.service.LogtoolService;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

	@Autowired 
	private LogtoolService logtoolService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String indexMapping() {
        return "redirect:/loglevel";
    }

    @RequestMapping(value = "/loglevel", method = RequestMethod.GET)
    public String loglevelMapping(ModelMap model) {
    	Map<String, Logger> loggers = logtoolService.getLoggersMap();
    	model.addAttribute("packages", loggers);
        return "/loglevel";
    }
}