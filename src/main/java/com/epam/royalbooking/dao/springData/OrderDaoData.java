package com.epam.royalbooking.dao.springData;

import com.epam.royalbooking.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDaoData extends CrudRepository<Order, Long> {
    void deleteById(long id);
}
