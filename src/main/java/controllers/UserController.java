package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.UserService;

@Controller
public class UserController {
    private UserService service;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.service.getAll());
        return "users";
    }

    /**
    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("users") User user) {
        if(user.getId() == 0) {
            this.service.save(user);
        } else {
            this.service.update(user)
        }
    }
    */



}
