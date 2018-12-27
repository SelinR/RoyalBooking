package entities;

import java.time.LocalDate;

public class Order {
    int orderId;
    Room bookedRoom;
    LocalDate entryDate;
    LocalDate leaveDate;
    double totalPrice;
    User user;
    OrderStatus status;

    public Order(int orderId, Room bookedRoom, LocalDate entryDate, LocalDate leaveDate, double totalPrice, User user, OrderStatus status) {
        this.orderId = orderId;
        this.bookedRoom = bookedRoom;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.totalPrice = totalPrice;
        this.user = user;
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
