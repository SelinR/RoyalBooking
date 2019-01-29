package com.epam.royalbooking.dto;

import com.epam.royalbooking.entities.Order;

public class OrderTransporter {

    private static Order order;
    private static boolean orderAvailable = false;

    public static Order getOrder() {
        orderAvailable = false;
        return order;
    }

    public static void setOrder(Order order) {
        OrderTransporter.order = order;
        orderAvailable = true;

    }

    public static boolean isOrderAvailable() {
        return orderAvailable;
    }

}

