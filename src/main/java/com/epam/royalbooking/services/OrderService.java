package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.OrderDAO;
import com.epam.royalbooking.dao.RoomDAO;
import com.epam.royalbooking.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public List<LocalDate> getAllBookedDatesByRoomId(int id) {
        List<LocalDate> bookedDates = new ArrayList<>();
        for (Order order : orderDAO.getAllOrdersByBookedRoomId(id)) {
            LocalDate entryDate = order.getEntryDate();
            LocalDate leaveDate = order.getLeaveDate();
            while (!entryDate.equals(leaveDate)) {
                bookedDates.add(entryDate);
                entryDate = entryDate.plusDays(1);
            }
        }
        return bookedDates;
    }

    public void delete(int id) {
        orderDAO.delete(id);
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

    public boolean isOrderDatesNotCrosses(Order orderToCheck, int bookingRoomId) {
        List<Order> ordersList = orderDAO.getAllOrdersByBookedRoomId(bookingRoomId);
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
