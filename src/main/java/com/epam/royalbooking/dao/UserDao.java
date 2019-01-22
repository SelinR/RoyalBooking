package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
