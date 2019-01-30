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
import java.util.Collections;
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
            while (entryDate.isBefore(leaveDate.plusDays(1))) {
                bookedDates.add(entryDate);
                entryDate = entryDate.plusDays(1);
            }
        }
        return bookedDates;
    }

    /**
     * @Transactional doesn't let to delete the old order inside this method.
     * @param order updated order
     */
    public boolean update(Order order) {
        Order temporalOrder = getById(order.getId());
        delete(order.getId());
        if (isOrderValid(order.getEntryDate(), order.getLeaveDate(), order.getBookedRoomID())) {
            order.setTotalPrice(calculateTotalPrice(order.getBookedRoomID(), order.getEntryDate(),
                    order.getLeaveDate()));
            orderDao.save(order);
            return true;
        } else {
            orderDao.save(temporalOrder);
            return false;
        }
    }

    @Transactional
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
        return entryDate != null && leaveDate != null
                && isEntryDateBeforeOrEqualLeaveDate(entryDate, leaveDate)
                && isOrderForTodayOrInFuture(entryDate)
                && isRoomFreeInSelectedDays(entryDate, leaveDate, bookingRoomId);
    }

    public boolean isEntryDateBeforeOrEqualLeaveDate(LocalDate entryDate, LocalDate leaveDate) {
        return entryDate.isBefore(leaveDate) || entryDate.isEqual(leaveDate);
    }

    public boolean isOrderForTodayOrInFuture(LocalDate entryDate) {
        LocalDate today = LocalDate.now();
        return entryDate.isEqual(today) || entryDate.isAfter(today);
    }

    public boolean isRoomFreeInSelectedDays(LocalDate entryLocalDate, LocalDate leaveLocalDate, int bookingRoomId) {
        long entryDate = entryLocalDate.toEpochDay();
        long leaveDate = leaveLocalDate.toEpochDay();
        List<Order> ordersList = orderDao.findAllByBookedRoomID(bookingRoomId);
        for (Order order : ordersList) {
            LocalDate existingEntryLocalDate = order.getEntryDate();
            LocalDate existingLeaveLocalDate = order.getLeaveDate();
            long existingEntryDate = existingEntryLocalDate.toEpochDay();
            long existingLeaveDate = existingLeaveLocalDate.toEpochDay();
            if ((entryDate >= existingEntryDate && entryDate <= existingLeaveDate)
                    || (leaveDate >= existingEntryDate && leaveDate <= existingLeaveDate)) {
                return false;
            }
            List<LocalDate> allBookingDates = getAllDaysBetweenIncludingLastDay(entryLocalDate, leaveLocalDate);
            List<LocalDate> allUnavailableDates = getAllDaysBetweenIncludingLastDay(existingEntryLocalDate, existingLeaveLocalDate);
            if (allBookingDates.equals(Collections.EMPTY_LIST) || allUnavailableDates.equals(Collections.EMPTY_LIST)) {
                return false;
            }
            for (LocalDate bookingDate : allBookingDates) {
                if (allUnavailableDates.stream().anyMatch(date -> date.equals(bookingDate))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * We need to add one day to leave date while calculating, because ChronoUnit.DAYS.between(first, second)
     * second parameter is exclusive.
     * @return Double - total price of order
     */
    public double calculateTotalPrice(int bookedRoomId, LocalDate entryDate, LocalDate leaveDate) {
        Optional<Room> room = roomDao.findById(bookedRoomId);
        if (room.isPresent()) {
            double dailyCost = room.get().getDailyCost();
            long days = ChronoUnit.DAYS.between(entryDate, leaveDate.plusDays(1));
            return days * dailyCost;
        } else {
            throw new RuntimeException("No room with such ID found: " + bookedRoomId);
        }
    }

    private Order createOrder(int id) {
        Optional<Order> optionalOrder = orderDao.findById(id);
        return optionalOrder.orElse(null);
    }

    public List<LocalDate> getAllDaysBetweenIncludingLastDay(LocalDate in, LocalDate out) {
        if (in == null || out == null || in.isAfter(out)) {
            return new ArrayList<>();
        }
        List<LocalDate> allDays = new ArrayList<>();
        while (in.isBefore(out)) {
            allDays.add(in);
            in = in.plusDays(1);
        }
        allDays.add(in);
        return allDays;
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
