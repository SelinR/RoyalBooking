package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    @Override
    List<User> findAll();
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
}
