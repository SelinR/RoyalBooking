package services;

import dao.daoimpl.OrderDAOImpl;
import dao.daoimpl.RoomDAOImpl;
import entities.Order;
import entities.Room;
import entities.User;
import enums.OrderStatus;
import enums.UserType;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

public class OrderService {
    private static OrderService instance = null;
    private OrderDAOImpl orderDAO = OrderDAOImpl.getInstance();
    private RoomDAOImpl roomDAO = RoomDAOImpl.getInstance();

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            return new OrderService();
        }
        return instance;
    }

    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    public void save(Order order) {
        orderDAO.save(order);
    }

    public Order create(HttpServletRequest request) {
        int roomId = -1;
        LocalDate entryDate = null;
        LocalDate leaveDate = null;
        String name = "";
        String surname = "";
        String email = "";

        Enumeration<String> parametersNames = request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String key = parametersNames.nextElement();
            String value = request.getParameter(key);
            if (key.equalsIgnoreCase("roomId")) {
                roomId = Integer.valueOf(value);
            } else if (key.equalsIgnoreCase("entryDate")) {
                entryDate = LocalDate.parse(value);
            } else if (key.equalsIgnoreCase("leaveDate")) {
                leaveDate = LocalDate.parse(value);
            } else if (key.equalsIgnoreCase("name")) {
                name = value;
            } else if (key.equalsIgnoreCase("surname")) {
                surname = value;
            }else if (key.equalsIgnoreCase("email")) {
                email = value;
            }
        }
        User user = new User(999, name, surname, "Lalaland", LocalDate.now(), "777", email, UserType.USER);
        int orderId = orderDAO.getAll().size();
        Room bookedRoom = roomDAO.getById(roomId);
        Double totalPrice = calculateTotalPrice();
        return new Order(orderId, bookedRoom, entryDate, leaveDate, totalPrice,user, OrderStatus.CREATED );
    }

    /**
     * I will code this method a little bit later
     * @return true if @param order is valid
     *
     */
    public boolean isOrderValid(Order order) {
        return true;
    }

    /**
     * I will code this method a little bit later
     * @return Double - total price of order
     */
    public Double calculateTotalPrice(){
        return 777.0;
    }
}
