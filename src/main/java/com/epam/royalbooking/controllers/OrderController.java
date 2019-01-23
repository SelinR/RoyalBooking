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
public class OrderController {
    private OrderService orderService;
    private RoomService roomService;
    private UserService userService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Order> orders = orderService.getAll();
        List<Room> rooms = roomService.getAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("orders", orders);
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "orders/orders";
    }

    /**
     * Method defines userID from Spring Context using @param principal,
     * and saves order in DataBase
     * if user fill wrong input dates , then returns wrong_dates_input.jsp page
     * @param order to save in DataBase
     * @param principal need to get user email and id
     */
    @RequestMapping(value = "order_save", method = RequestMethod.POST)
    public String save(@ModelAttribute("order") Order order, Principal principal) {
        if (orderService.isOrderValid(order, order.getBookedRoomID())) {
            String email = (principal.getName());
            User currentUser = userService.getByEmail(email);
            order.setUserID(currentUser.getId());
            orderService.save(order, false);
            return "redirect:/profile";
        } else {
            return "/ErrorPage";
        }
    }

    @RequestMapping(value = "/orders/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        orderService.delete(id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/orders/edit/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        model.addAttribute("orders", orderService.getAll());
        return "/orders";
    }

    @RequestMapping(value = "/order_creation")
    public String getOrderCreationPage(Model model, @ModelAttribute("roomToBookId") int roomToBookId) {
        model.addAttribute("list", orderService.getAllBookedDatesByBookedRoomId(roomToBookId));
        model.addAttribute("roomToBook", roomService.getById(roomToBookId));
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "orders/order_creation";
    }

    /**
     * Method calculates orders total price, based on dates and daily cost.
     * Refers to page with  order details and order confirmation.
     * if user fills wrong input dates , then returns wrong_dates_input.jsp page
     * @param order doesn't save in DataBase on this stage, haven't userID
     * @return ModelAndView with refer to order_confirm.jsp, and entity Order with calculated total price
     */
    @RequestMapping(value = "/order_confirm", method = RequestMethod.POST)
    public ModelAndView getOrderConfirmPage(@ModelAttribute("order") Order order) {
        if (orderService.isOrderValid(order, order.getBookedRoomID())) {
            order.setTotalPrice(orderService.calculateTotalPrice(order.getBookedRoomID(), order.getEntryDate(), order.getLeaveDate()));
            return new ModelAndView("orders/order_confirm", "order", order);
        } else if (!orderService.isOrderValid(order, order.getBookedRoomID())) {
            return new ModelAndView("/wrong_dates_input", "order", order);
        } else {
            return new ModelAndView("/ErrorPage");
        }
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
