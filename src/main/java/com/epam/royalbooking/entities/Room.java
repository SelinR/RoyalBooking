package com.epam.royalbooking.entities;

import com.epam.royalbooking.enums.RoomType;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_type")
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;
    @Column(name = "beds_amount")
    private int bedsAmount;
    private double area;
    @Column(name = "daily_cost")
    private double dailyCost;
    @Column(name = "additional_info")
    private String additionalInfo;

    public Room() {

    }

    public Room(RoomType roomType, int bedsAmount, double area, double dailyCost, String additionalInfo) {
        this.roomType = roomType;
        this.bedsAmount = bedsAmount;
        this.area = area;
        this.dailyCost = dailyCost;
        this.additionalInfo = additionalInfo;
    }

    public Room(int id, RoomType roomType, int bedsAmount, double area, double dailyCost, String additionalInfo) {
        this.id = id;
        this.roomType = roomType;
        this.bedsAmount = bedsAmount;
        this.area = area;
        this.dailyCost = dailyCost;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getBedsAmount() {
        return bedsAmount;
    }

    public void setBedsAmount(int bedsAmount) {
        this.bedsAmount = bedsAmount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}