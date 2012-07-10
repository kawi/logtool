package com.noname.logtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

    @RequestMapping(method = RequestMethod.GET)
    public String indexMapping() {
        return "redirect:/loglevel";
    }

    @RequestMapping(value = "/loglevel", method = RequestMethod.GET)
    public String loglevelMapping() {
        return "/loglevel";
    }
}