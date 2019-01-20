package com.epam.royalbooking.controllers;

import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.entities.Room;
import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.services.OrderService;
import com.epam.royalbooking.services.RoomService;
import com.epam.royalbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;
    private RoomService roomService;
    private UserService userService;

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

    @RequestMapping(value = "order_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("order") Order order) {
        if (orderService.isOrderValid(order)){
            order.setTotalPrice(orderService.calculateTotalPrice(order.getBookedRoomID(),order.getEntryDate(),order.getLeaveDate()));
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

    @RequestMapping(value = "/order_creation")
    public String getOrderCreationPage(Model model, @ModelAttribute("roomToBookId") int roomToBookId){
        model.addAttribute("roomToBook",roomService.getById(roomToBookId));
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "/order_creation";
    }

    @RequestMapping(value = "/order_confirm", method = RequestMethod.POST)
    public ModelAndView getOrderConfirmPage(@ModelAttribute("order") Order order) {
        if (orderService.isOrderValid(order)){
            /*======================
            and here we set the user id*/
            User authenticatedUser = findUser();
            order.setUserID(authenticatedUser.getId());
             /*=======================*/
            order.setTotalPrice(orderService.calculateTotalPrice(order.getBookedRoomID(),order.getEntryDate(),order.getLeaveDate()));
            return new ModelAndView("/order_confirm", "order", order);
        }
        else {
            return new ModelAndView("/ErrorPage");
        }
    }

    private User findUser() {
        /* Here we get the authenticated user */
        SecurityContext context = SecurityContextHolder.getContext();

        /* This is the Spring Security object called "User" with authentication details*/
        org.springframework.security.core.userdetails.User authenticatedUser =
                (org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal();
        String email = authenticatedUser.getUsername();

        /* This is our User class */
        return userService.getByEmail(email);
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
