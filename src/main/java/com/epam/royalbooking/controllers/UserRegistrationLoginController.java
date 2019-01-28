package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.User;
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
    public ModelAndView userRegistrationForm(ModelAndView modelAndView) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication().getPrincipal() instanceof String) {
            modelAndView.addObject("user", new User());
            modelAndView.addObject("passwordValidation", "");
            modelAndView.setViewName("registrationandlogin/registration");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView userRegistrationSubmit(@ModelAttribute User user,
                                               @ModelAttribute("passwordValidation") String passwordValidation) {
        ModelAndView modelAndView = new ModelAndView();
        if (user.getPassword().equals(passwordValidation)
                && !userService.isEmailExists(user.getEmail())
                && userService.isUserDataValid(user)) {
            userService.save(user);
            modelAndView.setViewName("redirect:/login");
        } else {
            modelAndView.addObject("name", user.getName());
            modelAndView.addObject("surname", user.getSurname());
            modelAndView.addObject("country", user.getCountry());
            modelAndView.addObject("birthday", user.getBirthday().toString());
            modelAndView.addObject("phone", user.getPhone());
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("redirect:/registration?error");
        }
        return modelAndView;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
