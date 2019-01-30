package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.services.OrderService;
import com.epam.royalbooking.services.RoomService;
import com.epam.royalbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {
    private UserService userService;
    private RoomService roomService;
    private OrderService orderService;

    @RequestMapping("profile")
    public String getById(Model model, Principal principal){
        model.addAttribute("user", getCurrentUser(principal));
        model.addAttribute("orders", orderService.getOrdersByUserId(getCurrentUser(principal).getId()));
        return "profile/details";
    }

    @RequestMapping(value = "profile/delete")
    public String delete(Principal principal) {
        userService.delete(getCurrentUser(principal).getId());
        return "redirect:/logout";
    }

    @RequestMapping("profile/edit")
    public String edit(Model model, Principal principal) {
        model.addAttribute("user", getCurrentUser(principal));
        return "profile/edit";
    }

    @RequestMapping(value = "profile/edit/submit", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/profile";
    }

    @RequestMapping("profile/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id, Principal principal) {
        User user = getCurrentUser(principal);
        Order order = orderService.getById(id);
        if (order == null) {
            return "redirect:/profile";
        } else {
            if (order.getUserID() == user.getId()) {
                orderService.delete(id);
            }
            return "redirect:/profile";
        }
    }

    private User getCurrentUser(Principal principal) {
        User currentUser = userService.getByEmail(principal.getName());
        return userService.getById(currentUser.getId());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
}
