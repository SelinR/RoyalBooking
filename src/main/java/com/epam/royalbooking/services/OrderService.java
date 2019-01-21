package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.OrderDAO;
import com.epam.royalbooking.dao.RoomDAO;
import com.epam.royalbooking.dao.jdbcDaoImpl.JdbcOrderDAOImpl;
import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.enums.OrderStatus;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Enumeration;
import java.util.List;

@Service
public class OrderService {
    private OrderDAO orderDAO;
    private RoomDAO roomDAO;

    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    public void save(Order order) {
        orderDAO.save(order);
    }

    public Order getById(int id) {
        return orderDAO.getById(id);
    }

    public void delete(int id) {
        orderDAO.delete(id);
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderDAO.getByUserId(userId);
    }
    /**
     * I will code this method a little bit later
     *
     * @return true if @param order is valid
     */
    public boolean isOrderValid(Order order) {
        LocalDate entryDate = order.getEntryDate();
        LocalDate leaveDate = order.getLeaveDate();
        return leaveDate.isAfter(entryDate);
    }

    /**
     * @return Double - total price of order
     */
    public double calculateTotalPrice(int bookedRoomId, LocalDate entryDate, LocalDate leaveDate) {
        double dailyCost = roomDAO.getById(bookedRoomId).getDailyCost();
        long days = ChronoUnit.DAYS.between(entryDate, leaveDate);
        return dailyCost * days;
    }

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void setRoomDAO(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
}
