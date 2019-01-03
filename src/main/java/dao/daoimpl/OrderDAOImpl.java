package dao.daoimpl;

import dao.OrderDAO;
import entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static OrderDAOImpl instance;
    private List<Order> orders;

    private OrderDAOImpl() {
        orders = new ArrayList<>();
    }

    public static OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    /**
     * It"s temporary methods implementation, till then we are working with ArrayList
     */
    @Override
    public Order getById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        throw new IllegalArgumentException(id + " id of order didnt found!");
    }

    /**
     * It"s temporary methods implementation, till then we are working with ArrayList
     */
    @Override
    public void save(Order order) {
        if (orders.size() != 0) {
            int idOfLastOrder = orders.get(orders.size() - 1).getId();
            order.setId(idOfLastOrder + 1);
            orders.add(order);
        } else{
            order.setId(0);
            orders.add(order);
        }
    }

    /**
     * It"s temporary methods implementation, till then we are working with ArrayList
     */
    @Override
    public void delete(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                orders.remove(id);
                return;
            }
        }
        throw new IllegalArgumentException(id + " id of order didnt found!");
    }
}
