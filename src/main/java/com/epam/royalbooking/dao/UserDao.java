package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    boolean existsUserByEmail(String email);
    Optional<User> findById(int id);
    void deleteById(int id);
}
