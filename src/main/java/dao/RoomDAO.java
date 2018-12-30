package dao;

import entities.Room;

import java.util.List;

public interface RoomDAO {
    List<Room> getAll();
    Room getById(int id);
    void save(Room room);
    void update(Room room);
    void delete(int id);
}