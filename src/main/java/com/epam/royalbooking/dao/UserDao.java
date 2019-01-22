package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
