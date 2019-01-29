package com.epam.royalbooking.entities;

import com.epam.royalbooking.enums.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "entry_date")
    private LocalDate entryDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "leave_date")
    private LocalDate leaveDate;
    @Column(name = "total_price")
    private double totalPrice;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Column(name = "user_id")
    private int userID;
    @Column(name = "booked_room_id")
    private int bookedRoomID;
    @Column(name = "prepaid")
    private boolean prepaid = false;

    public Order() {

    }

    public Order (int id, int bookedRoomID, LocalDate entryDate, LocalDate leaveDate, double totalPrice, int userID,
                  OrderStatus status){
        this.id = id;
        this.bookedRoomID = bookedRoomID;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.totalPrice = totalPrice;
        this.userID = userID;
        this.status = status;
    }

    public Order (int bookedRoomID, LocalDate entryDate, LocalDate leaveDate, double totalPrice, int userID,
                   OrderStatus status){
        this.bookedRoomID = bookedRoomID;
        this.entryDate = entryDate;
        this.leaveDate = leaveDate;
        this.totalPrice = totalPrice;
        this.userID = userID;
        this.status = status;
    }

    public Order(Order order) {
        this.id = order.id;
        this.bookedRoomID = order.bookedRoomID;
        this.entryDate = order.entryDate;
        this.leaveDate = order.leaveDate;
        this.totalPrice = order.totalPrice;
        this.userID = order.userID;
        this.status = order.status;
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

    public boolean isPrepaid() {
        return prepaid;
    }

    public void setPrepaid(boolean prepaid) {
        this.prepaid = prepaid;
    }
}
