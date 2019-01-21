package com.epam.royalbooking.dao.springData;

import com.epam.royalbooking.entities.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomDaoData extends CrudRepository<Room, Long> {
    void deleteById(int id);
    Optional<Room> findById(int id);
}
