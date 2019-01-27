package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends CrudRepository<Room, Integer> {
    @Override
    List<Room> findAll();
}
