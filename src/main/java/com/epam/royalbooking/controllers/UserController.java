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

    @RequestMapping(value = "view/users", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getAll());
        return "/users";
    }

    @RequestMapping("view/users/user/{id}")
    public String getById(@PathVariable("id") int id, Model model){
        model.addAttribute("book", userService.getById(id));
        return "/user";
    }

    @RequestMapping(value = "view/users", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user) {
        if(user.getId() == 0) {
            userService.save(user);
        } else {
            userService.update(user);
        }
        return "redirect:/view/users";
    }

    @RequestMapping(value = "view/users/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/view/users";
    }

    @RequestMapping(value = "view/users/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("users", userService.getAll());
        return "/users";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
