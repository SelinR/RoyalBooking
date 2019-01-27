package com.epam.royalbooking.config;

import com.epam.royalbooking.dao.OrderDao;
import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.enums.OrderStatus;
import com.epam.royalbooking.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderInitialChecker {
    private OrderDao orderDao;
    private OrderService orderService;

    public void checkExpiredOrders() {
        for (Order order : orderService.getAll()) {
            if (order.getLeaveDate().isBefore(LocalDate.now())) {
                order.setStatus(OrderStatus.EXPIRED);
                orderDao.save(order);
            }
        }
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
