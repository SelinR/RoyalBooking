package com.epam.royalbooking.controllers;

import com.epam.royalbooking.dto.OrderTransporter;
import com.epam.royalbooking.dto.PreOrder;
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
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class OrderController {

    private OrderService orderService;
    private RoomService roomService;
    private UserService userService;

    /**
     * Needed for internationalization.
     */
    @RequestMapping(value = "order/save/{entryDate}/{leaveDate}", method = RequestMethod.GET)
    public ModelAndView saveGet(@ModelAttribute("order") Order order,
                                @PathVariable("entryDate") LocalDate entryDate, @PathVariable("leaveDate") LocalDate leaveDate) {
        ModelAndView modelAndView = new ModelAndView("orders/details");
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
    @RequestMapping(value = "order/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("order") Order order, Principal principal) {
        if (orderService.isOrderValid(order.getEntryDate(), order.getLeaveDate(), order.getBookedRoomID())) {
            String email = (principal.getName());
            User currentUser = userService.getByEmail(email);
            order.setUserID(currentUser.getId());
            orderService.save(order, false);
            return "redirect:/profile";
        } else {
            return "/ErrorPage";
        }
    }

    @RequestMapping(value = "/order/creation/{roomId}")
    public String getOrderCreationPage(Model model, @PathVariable("roomId") int roomId) {
        model.addAttribute("list", orderService.getAllBookedDatesByBookedRoomId(roomId));
        model.addAttribute("room", roomService.getById(roomId));
        model.addAttribute("minDate", LocalDate.now());
        model.addAttribute("maxDate", LocalDate.now().plusYears(2));
        return "orders/creation";
    }

    /**
     * Needed for internationalization.
     */
    @RequestMapping(value = "order/confirm/{roomId}/{entryDate}/{leaveDate}", method = RequestMethod.GET)
    public ModelAndView getOrderConfirmPageGetMethod(@ModelAttribute("order") Order order, @PathVariable("roomId") int roomId,
                                                     @PathVariable("entryDate") String entryDateVar, @PathVariable("leaveDate") String leaveDateVar,
                                                     Principal principal) {
        prepareOrderAfterLocalizationAtConfirmPage(order, entryDateVar, leaveDateVar, principal, roomId);
        return new ModelAndView("orders/details", "order", order);
    }

    /**
     * Method calculates orders total price, based on dates and daily cost.
     * Refers to page with  order details and order confirmation.
     * if user fills wrong input dates , then returns a message
     * Order isn't saved in DataBase on this stage, haven't userID
     * @param preOrder consisting of the entered dates to order
     * @return ModelAndView with refer to details.jsp, and entity Order with calculated total price
     */
    @RequestMapping(value = "/order/confirm/{roomId}", method = RequestMethod.POST)
    public ModelAndView getOrderConfirmPage(@ModelAttribute("preOrder") PreOrder preOrder, @PathVariable("roomId") int roomId) {
        preOrder.setEntryAndLeaveDate();
        LocalDate entryDate = preOrder.getEntryDate();
        LocalDate leaveDate = preOrder.getLeaveDate();
        if (orderService.isOrderValid(entryDate, leaveDate, roomId)) {
            Order order = new Order();
            order.setBookedRoomID(roomId);
            order.setEntryDate(entryDate);
            order.setLeaveDate(leaveDate);
            order.setTotalPrice(orderService.calculateTotalPrice(order.getBookedRoomID(), order.getEntryDate(), order.getLeaveDate()));
            OrderTransporter.setOrder(order);
            return new ModelAndView("orders/details", "order", order);
        } else {
            return new ModelAndView("redirect:/order/creation/" + roomId + "?error");
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
