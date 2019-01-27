package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends CrudRepository<Order, Integer> {
    @Override
    List<Order> findAll();
    List<Order> findByUserID(int id);
    List<Order> findAllByBookedRoomID(int id);
}
