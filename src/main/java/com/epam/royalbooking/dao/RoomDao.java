package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomDao extends CrudRepository<Room, Integer> {
}
