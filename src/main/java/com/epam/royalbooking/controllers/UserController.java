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
public class UserController {
    private UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAll());
        return "users/list";
    }

    @RequestMapping("user/{id}")
    public String getById(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "users/one";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "users/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "users/one";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}