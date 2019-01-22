package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.entities.Room;
import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.services.OrderService;
import com.epam.royalbooking.services.RoomService;
import com.epam.royalbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminOrdersController {
    private OrderService orderService;
    private RoomService roomService;
    private UserService userService;

    @RequestMapping("admin/orders")
    public String getAll(Model model) {
        List<Order> orders = orderService.getAll();
        List<Room> rooms = roomService.getAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("orders", orders);
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "admin/orders/list";
    }


    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
