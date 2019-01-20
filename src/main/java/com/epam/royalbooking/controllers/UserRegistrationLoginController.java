package com.epam.royalbooking.controllers;

import com.epam.royalbooking.dto.PasswordValidation;
import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.enums.UserType;
import com.epam.royalbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserRegistrationLoginController {
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String userLoginForm(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication().getPrincipal() instanceof String) {
            model.addAttribute("user", new User());
            return "registrationandlogin/login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String userLoginSubmit(@ModelAttribute User user) {
        return "home_page";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String userRegistrationForm(Model userModel, Model passwordValidationModel) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication().getPrincipal() instanceof String) {
            userModel.addAttribute("user", new User());
            passwordValidationModel.addAttribute("passwordValidation", new PasswordValidation());
            return "registrationandlogin/registration";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView userRegistrationSubmit(@ModelAttribute User user,
                                               @ModelAttribute PasswordValidation passwordValidation) {
        ModelAndView modelAndView = new ModelAndView();
        if (user.getPassword().equals(passwordValidation.getPasswordValidation())) {
            user.setUserType(UserType.USER);
            userService.save(user);
            modelAndView.addObject("user");
            modelAndView.setViewName("redirect:/login");
        } else {
            modelAndView.setViewName("redirect:/registration?error");
        }
        return modelAndView;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
