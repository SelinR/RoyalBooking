package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.enums.UserType;
import com.epam.royalbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRegistrationLoginController {
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String userLoginForm(Model model) {
        System.out.println("kbjhb");
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String userLoginSubmit(@ModelAttribute User user) {

        System.out.println("Home page");

        return "home_page";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String userRegistrationForm(Model userModel) {
        userModel.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String userRegistrationSubmit(@ModelAttribute User user) {
        user.setUserType(UserType.USER);
        userService.save(user);
        return "redirect:/";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
