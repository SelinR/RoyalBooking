package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.royalbooking.enums.OrderStatus.ACCEPTED;

public class OrderDaoTest {
    private List<Order> orders;

    public OrderDaoTest() {
        orders = new ArrayList<>();
        orders.add(new Order(1, 1, LocalDate.of(2019, 1, 27), LocalDate.of(2019, 1, 28), 50, 1, ACCEPTED));
        orders.add(new Order(2, 2, LocalDate.of(2019, 1, 27), LocalDate.of(2019, 1, 29), 160, 2, ACCEPTED));
        orders.add(new Order(3, 3, LocalDate.of(2019, 1, 27), LocalDate.of(2019, 1, 30), 300, 3, ACCEPTED));
        orders.add(new Order(4, 4, LocalDate.of(2019, 1, 27), LocalDate.of(2019, 1, 31), 480, 4, ACCEPTED));
        orders.add(new Order(5, 5, LocalDate.of(2019, 1, 29), LocalDate.of(2019, 1, 31), 200, 1, ACCEPTED));
        orders.add(new Order(6, 6, LocalDate.of(2019, 1, 30), LocalDate.of(2019, 2, 3), 200, 2, ACCEPTED));
    }

    public List<Order> getOrders() {
        return orders;
    }
}
