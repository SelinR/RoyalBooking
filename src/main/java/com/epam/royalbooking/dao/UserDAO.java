package com.epam.royalbooking.dao;

import com.epam.royalbooking.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getById(int id);
    User getByEmail(String email);
    boolean isEmailFree(String email);
    void save(User user);
    void update(User user);
    void delete(int id);
}
