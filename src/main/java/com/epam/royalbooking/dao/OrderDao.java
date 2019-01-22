package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDao extends CrudRepository<Order, Integer> {
}
