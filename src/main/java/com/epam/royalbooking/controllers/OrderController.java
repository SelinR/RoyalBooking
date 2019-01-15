package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.entities.Room;
import com.epam.royalbooking.services.OrderService;
import com.epam.royalbooking.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private RoomService roomService;

    @RequestMapping(value = "view/orders", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Order> orders = orderService.getAll();
        List<Room> rooms = roomService.getAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("orders", orders);
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "/orders";
    }

    @RequestMapping(value = "view/orders/order/{id}")
    public String getById(@PathVariable("id") int id, Model model){
        model.addAttribute("order", orderService.getById(id));
        return "/order";
    }

    @RequestMapping(value = "view/orders", method = RequestMethod.POST)
    public String save(@ModelAttribute("order") Order order) {
        if (orderService.isOrderValid(order)){
            orderService.save(order);
            return "redirect:/view/orders";
        }
        else {
            return "/ErrorPage";
        }
    }

    @RequestMapping(value = "view/orders/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        orderService.delete(id);
        return "redirect:/view/orders";
    }

    @RequestMapping(value = "view/orders/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        model.addAttribute("orders", orderService.getAll());
        return "/orders";
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
