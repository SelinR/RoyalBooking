package com.epam.royalbooking.services;

import util.DateFormatter;
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
    public boolean isOrderValid(LocalDate entryDate, LocalDate leaveDate, int bookingRoomId) {
        if (entryDate == null || leaveDate == null) {
            return false;
        } else {
            return isEntryDateBeforeLeaveDate(entryDate, leaveDate)
                        && isOrderForTodayOrInFuture(entryDate)
                        && isRoomFreeInSelectedDays(entryDate, leaveDate, bookingRoomId);
        }
    }

    private boolean isEntryDateBeforeLeaveDate(LocalDate entryDate, LocalDate leaveDate) {
        return entryDate.isBefore(leaveDate);
    }

    private boolean isOrderForTodayOrInFuture(LocalDate entryDate) {
        LocalDate today = LocalDate.now();
        return entryDate.isEqual(today) || entryDate.isAfter(today);
    }

    private boolean isRoomFreeInSelectedDays(LocalDate entryLocalDate, LocalDate leaveLocalDate, int bookingRoomId) {
        long entryDate = entryLocalDate.toEpochDay();
        long leaveDate = leaveLocalDate.toEpochDay();
        List<Order> ordersList = orderDao.findAllByBookedRoomID(bookingRoomId);
        for (Order order : ordersList) {
            long existingEntryDate = order.getEntryDate().toEpochDay();
            long existingLeaveDate = order.getLeaveDate().toEpochDay();
            if ((entryDate >= existingEntryDate && entryDate <= existingLeaveDate)
                    || (leaveDate >= existingEntryDate && leaveDate <= existingLeaveDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Double - total price of order
     */
    public double calculateTotalPrice(int bookedRoomId, LocalDate entryDate, LocalDate leaveDate) {
        Optional<Room> room = roomDao.findById(bookedRoomId);
        if (room.isPresent()) {
            double dailyCost = room.get().getDailyCost();
            long days = ChronoUnit.DAYS.between(entryDate, leaveDate);
            return days == 0 ? dailyCost : days * dailyCost;
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
