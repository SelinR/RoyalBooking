package com.epam.royalbooking.controllers;

import com.epam.royalbooking.dto.OrderTransporter;
import com.epam.royalbooking.dto.PreOrder;
import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.PrinterIOException;
import java.security.Principal;

@Controller
public class PaymentController {
    private OrderService orderService;

    /**
     * This method takes order from OrderTransporter class, which takes it from OrderController.
     */
    @RequestMapping("order/payment")
    public String getPaymentPage(Model model) {
        Order order;
        if (OrderTransporter.isOrderAvailable()) {
            order = OrderTransporter.getOrder();
        } else {
            return "ErrorPage";
        }
        model.addAttribute("order", order);
        return "orders/payment";
    }

    @RequestMapping(value = "order/confirm", method = RequestMethod.POST)
    public ModelAndView getConfirmationPage(@ModelAttribute("order") Order order, Principal principal) {
            ModelAndView modelAndView = new ModelAndView("redirect:/order/save");
            modelAndView.addObject("order", order);
            modelAndView.addObject("principal", principal);
        return modelAndView;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
