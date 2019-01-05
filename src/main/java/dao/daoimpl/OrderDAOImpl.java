package dao.daoimpl;

import dao.OrderDAO;
import entities.Order;
import entities.Room;
import entities.User;
import enums.OrderStatus;
import enums.UserType;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class OrderDAOImpl implements OrderDAO {
    private static OrderDAOImpl instance;
    private List<Order> orders;

    /**
     * Temporary. The field required to create random users and fill Order List
     */
    private static RoomDAOImpl roomDAOimlInstance = RoomDAOImpl.getInstance();

    /**
     * Temporary. The field is required to create random users and fill Order List
     */
    private static UserDAOImpl userDAOimlInstance = UserDAOImpl.getInstance();

    private OrderDAOImpl() {
        orders = new ArrayList<>();
        fillOrders();
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
        } else {
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

    /**
     * It"s temporary method
     */
    private void fillOrders() {
        final List<Room> rooms = roomDAOimlInstance.getAll();
        final int sizeOfRoomsList = rooms.size();
        LocalDate entryDate = LocalDate.now();
        LocalDate leaveDate = entryDate.plusDays(1);

        for (int i = 0; i < sizeOfRoomsList; i++) {
            User userExample = new User(i, "Boris", "Britva", "England", LocalDate.now(), "911", "hrenPopadesh@killer.com", UserType.USER);
            Order randomOrder = new Order(i, rooms.get(i), entryDate.plusDays(i),leaveDate.plusDays(i), 100, userExample, OrderStatus.CREATED);
            orders.add(randomOrder);
        }
    }
}
