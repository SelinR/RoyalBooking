package services;

import dao.RoomDAO;
import entities.Room;

import java.util.List;

public class RoomService {
    private RoomDAO dao;

    public List<Room> getAll() {
        return dao.getAll();
    }

    public void save(Room room) {
        dao.save(room);
    }

    public void update(Room room) {
        dao.update(room);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public Room getById(int id) {
        return dao.getById(id);
    }

    public void setDao(RoomDAO dao) {
        this.dao = dao;
    }

    public RoomDAO getDao() {
        return dao;
    }
}