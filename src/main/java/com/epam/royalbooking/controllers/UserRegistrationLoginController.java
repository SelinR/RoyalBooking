package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.enums.UserType;
import com.epam.royalbooking.services.UserService;
import com.epam.royalbooking.validation.UserValidation;
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
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String userLoginSubmit(@ModelAttribute User user) {
        return null;
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String userRegistrationForm(Model userModel, Model userValidationModel) {
        userModel.addAttribute("user", new User());
        userValidationModel.addAttribute("passwordValidation", new UserValidation());
        return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String userRegistrationSubmit(@ModelAttribute User user, @ModelAttribute UserValidation userValidation) {
        if (userService.isSubmitRequestValid(user, userValidation)) {
            user.setUserType(UserType.USER);
            userService.save(user);
            return "redirect:/";
        } else {
            return "registration";
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
