package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.UserDAO;
import com.epam.royalbooking.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserDAO dao;

    public List<User> getAll() {
        return dao.getAll();
    }

    public User getById(int id) {
        return dao.getById(id);
    }

    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }

    public void save(User user) {
        dao.save(user);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void update(User user) {
        dao.update(user);
    }

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public boolean isEmailFree(String email) {
        return dao.isEmailFree(email);
    }
}
