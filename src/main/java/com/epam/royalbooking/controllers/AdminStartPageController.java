package com.epam.royalbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminStartPageController {

    @RequestMapping(value = "/adminpage")
    public String goAdmin() {
        return "adminpage";
    }
}
