package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderDao extends CrudRepository<Order, Long> {
    void deleteById(int id);
    Optional<Order> findById(int id);
}
