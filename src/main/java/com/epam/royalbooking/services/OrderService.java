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
import java.util.ArrayList;
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
    public void save(Order order, boolean isCreatedByAdmin) {
        if (!isCreatedByAdmin) {
            order.setStatus(OrderStatus.ACCEPTED);
        }
            orderDao.save(order);
    }

    public Order getById(int id) {
        return createOrder(id);
    }

    @Transactional
    public List<LocalDate> getAllBookedDatesByBookedRoomId(int id) {
        List<LocalDate> bookedDates = new ArrayList<>();
        for (Order order : orderDao.findAllByBookedRoomID(id)) {
            LocalDate entryDate = order.getEntryDate();
            LocalDate leaveDate = order.getLeaveDate();
            while (!entryDate.equals(leaveDate)) {
                bookedDates.add(entryDate);
                entryDate = entryDate.plusDays(1);
            }
        }
        return bookedDates;
    }

    public void update(Order order) {
        orderDao.save(order);
    }

    public void delete(int id) {
        orderDao.deleteById(id);
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderDao.findByUserID(userId);
    }

    /**
     * @return true if @param order is valid
     */
    public boolean isOrderValid(Order order, int bookingRoomId) {
        LocalDate entryDate = order.getEntryDate();
        LocalDate leaveDate = order.getLeaveDate();
        boolean simpleValid = leaveDate.isAfter(entryDate);
        return simpleValid & isOrderDatesNotCrosses(order, bookingRoomId);
    }

    private boolean isOrderDatesNotCrosses(Order orderToCheck, int bookingRoomId) {
        List<Order> ordersList = orderDao.findAllByBookedRoomID(bookingRoomId);
        LocalDate entryDateToCheck = orderToCheck.getEntryDate();
        LocalDate leaveDateToCheck = orderToCheck.getLeaveDate();
        for (Order order : ordersList) {
            LocalDate existingEntryDate = order.getEntryDate();
            LocalDate existingLeaveDate = order.getLeaveDate();
            if (existingEntryDate.isAfter(entryDateToCheck) & existingEntryDate.isBefore(leaveDateToCheck)) {
                return false;
            } else if (existingLeaveDate.isAfter(entryDateToCheck) & existingLeaveDate.isBefore(leaveDateToCheck)) {
                return false;
            } else if (existingEntryDate.isEqual(entryDateToCheck) || existingLeaveDate.isEqual(leaveDateToCheck)) {
                return false;
            }
        }
        return true;
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
        return optionalOrder.orElse(null);
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
