package com.epam.royalbooking.entities;

import com.epam.royalbooking.enums.OrderStatus;

import java.time.LocalDate;

public class Order {
    private int id;
    private LocalDate entryDate;
    private LocalDate leaveDate;
    private double totalPrice;
    private OrderStatus status;
    private int userID;
    private int bookedRoomID;

    public Order() {

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
