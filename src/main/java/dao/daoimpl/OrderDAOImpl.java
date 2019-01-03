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
        return orders.get(id);
    }

    /**
     * It"s temporary methods implementation, till then we are working with ArrayList
     */
    @Override
    public void save(Order order) {
        order.setId(orders.size());
        orders.add(order);
    }

    /**
     * It"s temporary methods implementation, till then we are working with ArrayList
     */
    @Override
    public void delete(int id) {
        orders.remove(id);
    }
}
