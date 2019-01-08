package entities;

import enums.OrderStatus;

import java.time.LocalDate;

public class Order {
    private int id;
    private Room bookedRoom;
    private LocalDate entryDate;
    private LocalDate leaveDate;
    private double totalPrice;
    private User user;
    private OrderStatus status;
    private int userID;
    private int bookedRoomID;

    public Order(int id, Room bookedRoom, LocalDate entryDate, LocalDate leaveDate, double totalPrice, User user, OrderStatus status) {
        this.id = id;
        this.bookedRoom = bookedRoom;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.totalPrice = totalPrice;
        this.user = user;
        this.status = status;
    }
    public Order (int id, int bookedRoomID, LocalDate entryDate, LocalDate leaveDate, double totalPrice, int userID, OrderStatus status){
        this.id = id;
        this.bookedRoomID = bookedRoomID;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.totalPrice = totalPrice;
        this.userID = userID;
        this.status = status;
    }
    public Order ( int bookedRoomID, LocalDate entryDate, LocalDate leaveDate, double totalPrice, int userID, OrderStatus status){
        this.bookedRoomID = bookedRoomID;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.totalPrice = totalPrice;
        this.userID = userID;
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBookedRoomID() {
        return bookedRoomID;
    }

    public void setBookedRoomID(int bookedRoomID) {
        this.bookedRoomID = bookedRoomID;
    }
}
