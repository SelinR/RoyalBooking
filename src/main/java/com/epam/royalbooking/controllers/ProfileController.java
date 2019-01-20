package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
    private UserService userService;

    @RequestMapping("profile{id}")
    public String getById(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "profile/details";
    }

    @RequestMapping(value = "profile{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("profile{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "profile/edit";
    }

    @RequestMapping(value = "profile/edit/submit", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "profile/details";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
