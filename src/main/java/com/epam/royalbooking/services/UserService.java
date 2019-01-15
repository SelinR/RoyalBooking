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

    public void save(User user) {
        dao.save(user);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
}
