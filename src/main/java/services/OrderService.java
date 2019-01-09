package services;

import dao.OrderDAO;
import dao.jdbcDaoImpl.JdbcOrderDAOImpl;
import entities.Order;
import enums.OrderStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

public class OrderService {
    private static OrderService instance = null;
    private OrderDAO orderDAO;

    private OrderService() {
        orderDAO = JdbcOrderDAOImpl.getInstance();
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
        int bookedRoomId = -1;
        LocalDate entryDate = null;
        LocalDate leaveDate = null;
        int userID = -1;

        Enumeration<String> parametersNames = request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String key = parametersNames.nextElement();
            String value = request.getParameter(key);
            if (key.equalsIgnoreCase("roomId")) {
                bookedRoomId = Integer.valueOf(value);
            } else if (key.equalsIgnoreCase("entryDate")) {
                entryDate = LocalDate.parse(value);
            } else if (key.equalsIgnoreCase("leaveDate")) {
                leaveDate = LocalDate.parse(value);
            } else if (key.equalsIgnoreCase("userID")){
                userID = Integer.valueOf(value);
            }
        }
        Double totalPrice = calculateTotalPrice();
        return new Order(bookedRoomId, entryDate, leaveDate, totalPrice,userID, OrderStatus.ACCEPTED );
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
