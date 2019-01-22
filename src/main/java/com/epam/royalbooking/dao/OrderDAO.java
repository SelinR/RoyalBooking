package com.epam.royalbooking.dao;


import com.epam.royalbooking.entities.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAll();
    Order getById(int id);
    List<Order> getAllOrdersByBookedRoomId(int id);
    void save(Order order);
    void update(Order order);
    void delete(int id);

}
