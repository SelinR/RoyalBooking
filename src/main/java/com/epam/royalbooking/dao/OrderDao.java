package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Integer> {
    @Override
    List<Order> findAll();
}
