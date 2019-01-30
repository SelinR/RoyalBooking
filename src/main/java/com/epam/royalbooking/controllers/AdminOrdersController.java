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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        List<User> users = userService.getAll();
        model.addAttribute("order", new Order());
        model.addAttribute("rooms", rooms);
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "admin/orders/list";
    }

    @RequestMapping("admin/order/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        List<Room> rooms = roomService.getAll();
        List<User> users = userService.getAll();
        model.addAttribute("order", orderService.getById(id));
        model.addAttribute("rooms", rooms);
        model.addAttribute("users", users);
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "admin/orders/details";
    }

    @RequestMapping(value = "admin/orders/add", method = RequestMethod.POST)
    public String save(@ModelAttribute("order") Order order) {
        if (orderService.isOrderValid(order.getEntryDate(), order.getLeaveDate(), order.getBookedRoomID())) {
            order.setTotalPrice(orderService.calculateTotalPrice(order.getBookedRoomID(),
                    order.getEntryDate(), order.getLeaveDate()));
            orderService.save(order, true);
            return "redirect:/admin/orders";
        } else {
            return "ErrorPage";
        }
    }

    @RequestMapping(value = "admin/orders/edit", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("order") Order order, ModelAndView modelAndView) {
        Order oldOrder = orderService.getById(order.getId());
        if (orderService.update(order)) {
            modelAndView.addObject("order", order);
            modelAndView.setViewName("redirect:/admin/orders/");
            return modelAndView;
        } else {
            modelAndView.addObject("order", oldOrder);
            modelAndView.setViewName("redirect:/admin/orders/");
            return modelAndView;
        }
    }

    @RequestMapping("admin/orders/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        orderService.delete(id);
        return "redirect:/admin/orders";
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
