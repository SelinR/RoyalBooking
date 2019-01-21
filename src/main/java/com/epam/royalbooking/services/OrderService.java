package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.springData.OrderDaoData;
import com.epam.royalbooking.dao.springData.RoomDaoData;
import com.epam.royalbooking.entities.Order;
import com.epam.royalbooking.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class OrderService {
    private OrderDaoData orderDao;
    private RoomDaoData roomDao;

    public Iterable<Order> getAll() {
        return orderDao.findAll();
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public Optional<Order> getById(long id) {
        return orderDao.findById(id);
    }

    public void delete(Order order) {
        orderDao.delete(order);
    }

    public void delete(long id) {
        orderDao.deleteById(id);
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
    public double calculateTotalPrice(long bookedRoomId, LocalDate entryDate, LocalDate leaveDate) {
        Optional<Room> room = roomDao.findById(bookedRoomId);
        if (room.isPresent()) {
            double dailyCost = room.get().getDailyCost();
            long days = ChronoUnit.DAYS.between(entryDate, leaveDate);
            return dailyCost * days;
        } else {
            throw new RuntimeException("No room with such ID found: " + bookedRoomId);
        }
    }

    @Autowired
    public void setOrderDao(OrderDaoData orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setRoomDao(RoomDaoData roomDao) {
        this.roomDao = roomDao;
    }
}
