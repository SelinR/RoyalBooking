package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomDao extends CrudRepository<Room, Long> {
    void deleteById(int id);
    Optional<Room> findById(int id);
}
