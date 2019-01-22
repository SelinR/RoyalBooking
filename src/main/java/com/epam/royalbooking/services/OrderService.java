package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.OrderDao;
import com.epam.royalbooking.dao.RoomDao;
import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.entities.Room;
import com.epam.royalbooking.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderDao orderDao;
    private RoomDao roomDao;

    public List<Order> getAll() {
        return orderDao.findAll();
    }

    @Transactional
    public void save(Order order) {
        order.setStatus(OrderStatus.ACCEPTED);
        orderDao.save(order);
    }

    public Order getById(int id) {
        return createOrder(id);
    }

    @Transactional
    public void delete(int id) {
        orderDao.deleteById(id);
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.findOrdersByUserID();
    }
    /**
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
        Optional<Room> room = roomDao.findById(bookedRoomId);
        if (room.isPresent()) {
            double dailyCost = room.get().getDailyCost();
            long days = ChronoUnit.DAYS.between(entryDate, leaveDate);
            return dailyCost * days;
        } else {
            throw new RuntimeException("No room with such ID found: " + bookedRoomId);
        }
    }

    private Order createOrder(int id) {
        Optional<Order> optionalOrder = orderDao.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new RuntimeException("Could not create order with id: " + id);
        }
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }
}
