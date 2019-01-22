package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.RoomDao;
import com.epam.royalbooking.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private RoomDao dao;

    public List<Room> getAll() {
        return createRoomsList();
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
    public void delete(int id) {
        dao.deleteById(id);
    }

    public Room getById(int id) {
        return createRoom(id);
    }

    private Room createRoom(int id) {
        Optional<Room> room = dao.findById(id);
        if (room.isPresent()) {
            return room.get();
        } else {
            throw new RuntimeException("No room found.");
        }
    }

    private List<Room> createRoomsList() {
        Iterable<Room> roomIterable = dao.findAll();
        List<Room> rooms = new ArrayList<>();
        roomIterable.forEach(rooms::add);
        return rooms;
    }

    @Autowired
    public void setDaoData(RoomDao dao) {
        this.dao = dao;
    }
}