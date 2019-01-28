package com.epam.royalbooking.controllers;

import com.epam.royalbooking.dto.OrderTransporter;
import com.epam.royalbooking.entities.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PaymentController {

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
    public ModelAndView getConfirmationPage(@ModelAttribute("order") Order order) {
        ModelAndView modelAndView = new ModelAndView("forward:/order/save");
        order.setPrepaid(true);
        modelAndView.addObject("order", order);
        return modelAndView;
        //TODO fix prepayment
    }
}
