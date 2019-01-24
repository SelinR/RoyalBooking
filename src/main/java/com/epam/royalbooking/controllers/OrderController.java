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
     * Needed for internationalization.
     */
    @RequestMapping(value = "order_save/{entryDate}/{leaveDate}", method = RequestMethod.GET)
    public ModelAndView saveGet(@ModelAttribute("order") Order order,
                                @PathVariable("entryDate") LocalDate entryDate, @PathVariable("leaveDate") LocalDate leaveDate) {
        ModelAndView modelAndView = new ModelAndView("orders/order_confirm");
        modelAndView.addObject("order", order);
        modelAndView.addObject("entryDate", entryDate);
        modelAndView.addObject("leaveDate", leaveDate);
        return modelAndView;
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
            orderService.save(order);
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

    @RequestMapping(value = "/order_creation/{roomId}")
    public String getOrderCreationPage(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("list", orderService.getAllBookedDatesByBookedRoomId(roomId));
        model.addAttribute("room", roomService.getById(roomId));
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "orders/order_creation";
    }

    /**
     * Needed for internationalization.
     * Deprecated: I couldn't beat this damn data saving. Can't
     */
    @Deprecated
    @RequestMapping(value = "order_confirm/{roomId}/{entryDate}/{leaveDate}", method = RequestMethod.GET)
    public ModelAndView getOrderConfirmPageGetMethod(@ModelAttribute("order") Order order, @PathVariable("roomId") int roomId,
                                                     @PathVariable("entryDate") String entryDateVar, @PathVariable("leaveDate") String leaveDateVar,
                                                     Principal principal) {
        prepareOrderAfterLocalizationAtConfirmPage(order, entryDateVar, leaveDateVar, principal, roomId);
        return new ModelAndView("orders/order_confirm", "order", order);
    }

    /**
     * Method calculates orders total price, based on dates and daily cost.
     * Refers to page with  order details and order confirmation.
     * if user fills wrong input dates , then returns wrong_dates_input.jsp page
     * @param order doesn't save in DataBase on this stage, haven't userID
     * @return ModelAndView with refer to order_confirm.jsp, and entity Order with calculated total price
     */
    @RequestMapping(value = "/order_confirm/{roomId}", method = RequestMethod.POST)
    public ModelAndView getOrderConfirmPage(@ModelAttribute("order") Order order, @PathVariable("roomId") int roomId) {
        if (orderService.isOrderValid(order, order.getBookedRoomID())) {
            order.setBookedRoomID(roomId);
            order.setTotalPrice(orderService.calculateTotalPrice(order.getBookedRoomID(), order.getEntryDate(), order.getLeaveDate()));
            return new ModelAndView("orders/order_confirm", "order", order);
        } else if (!orderService.isOrderValid(order, order.getBookedRoomID())) {
            return new ModelAndView("/wrong_dates_input", "order", order);
        } else {
            return new ModelAndView("/ErrorPage");
        }
    }

    private void prepareOrderAfterLocalizationAtConfirmPage(Order order, String entryDateVar, String leaveDateVar,
                                                            Principal principal, int roomId) {
        LocalDate entryDate = LocalDate.parse(entryDateVar);
        LocalDate leaveDate = LocalDate.parse(leaveDateVar);
        User user = userService.getByEmail(principal.getName());
        order.setEntryDate(entryDate);
        order.setUserID(user.getId());
        order.setLeaveDate(leaveDate);
        order.setBookedRoomID(roomId);
        order.setTotalPrice(orderService.calculateTotalPrice(roomId, entryDate, leaveDate));
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
