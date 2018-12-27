package entities;

<<<<<<< HEAD
public class Room {
}
=======
/**
 * Room class.
 * {@code area} - room area in square meters.
 */
public class Room {
    private int roomId;
    private RoomType roomType;
    private int bedsAmount;
    private double area;
    private double dailyCost;
    private String additionalInfo;

    public Room() {

    }

    public Room(int roomId, RoomType roomType, int bedsAmount, double area, double dailyCost, String additionalInfo) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.bedsAmount = bedsAmount;
        this.area = area;
        this.dailyCost = dailyCost;
        this.additionalInfo = additionalInfo;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
>>>>>>> 84b02eb... RB-4: Reworked dailyCost type, roomType type, removed isFree field.
