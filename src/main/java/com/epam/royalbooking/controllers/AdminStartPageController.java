package com.epam.royalbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminStartPageController {

    @RequestMapping(value = "admin")
    public String goAdmin() {
        return "admin/adminpage";
    }
}
