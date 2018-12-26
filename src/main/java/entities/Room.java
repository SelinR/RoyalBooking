package entities;

import java.math.BigDecimal;

/**
 * Room class.
 * {@code area} - room area in square meters.
 */
public class Room {
    private int id;
    private String roomClass;
    private int bedsAmount;
    private double area;
    private BigDecimal dailyCost;
    private boolean isFree;

    public Room() {

    }

    public Room(String roomClass, int bedsAmount, double area, BigDecimal dailyCost, boolean isFree) {
        this.roomClass = roomClass;
        this.bedsAmount = bedsAmount;
        this.area = area;
        this.dailyCost = dailyCost;
        this.isFree = isFree;
    }

    public int getId() {
        return id;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
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

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}