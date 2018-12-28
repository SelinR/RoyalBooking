package dao;

import entities.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> getAll();
    Room getById();
    void save();
    void delete();
}