package com.epam.royalbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UtilityController {

    @RequestMapping(value = "/")
    public String welcome() {
        return "index";
    }
}
