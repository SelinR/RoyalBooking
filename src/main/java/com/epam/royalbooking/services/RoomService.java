package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.RoomDAO;
import com.epam.royalbooking.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Autowired
    public void setDao(RoomDAO dao) {
        this.dao = dao;
    }
}