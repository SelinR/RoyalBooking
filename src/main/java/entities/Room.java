package entities;

import java.math.BigDecimal;

/**
 * Room class.
 * {@code area} - room area in square meters.
 */
public class Room {
    private int roomId;
    private String roomClass;
    private int bedsAmount;
    private double area;
    private BigDecimal dailyCost;
    private String additionalInfo;
    private boolean isFree;

    public Room() {

    }

    public Room(int roomId, String roomClass, int bedsAmount, double area, BigDecimal dailyCost, String additionalInfo, boolean isFree) {
        this.roomId = roomId;
        this.roomClass = roomClass;
        this.bedsAmount = bedsAmount;
        this.area = area;
        this.dailyCost = dailyCost;
        this.additionalInfo = additionalInfo;
        this.isFree = isFree;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}