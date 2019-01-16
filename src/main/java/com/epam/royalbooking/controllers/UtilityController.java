package com.epam.royalbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UtilityController {

    @RequestMapping(value = "/")
    public String hello() {
        return "home";
    }

    @RequestMapping(value = "/adminpage")
    public String goAdmin() {
        return "adminpage";
    }
}
