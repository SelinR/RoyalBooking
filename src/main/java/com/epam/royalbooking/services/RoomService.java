package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.springData.RoomDaoData;
import com.epam.royalbooking.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoomService {
    private RoomDaoData dao;

    public Iterable<Room> getAll() {
        return dao.findAll();
    }

    @Transactional
    public void save(Room room) {
        dao.save(room);
    }

    @Transactional
    public void update(Room room) {
        dao.save(room);
    }

    @Transactional
    public void delete(Room room) {
        dao.delete(room);
    }

    @Transactional
    public void delete(int id) {
        dao.deleteById(id);
    }

    public Room getById(int id) {
        return getRoom(dao.findById(id));
    }

    private Room getRoom(Optional<Room> room) {
        if (room.isPresent()) {
            return room.get();
        } else {
            throw new RuntimeException("No room found.");
        }
    }

    @Autowired
    public void setDaoData(RoomDaoData dao) {
        this.dao = dao;
    }
}