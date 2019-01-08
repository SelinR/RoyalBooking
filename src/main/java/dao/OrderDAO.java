package dao;


import entities.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAll();
    Order getById(int id);
    void save(Order order);
    void update(Order order);
    void delete(int id);

}
