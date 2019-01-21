package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.springData.UserDaoData;
import com.epam.royalbooking.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private UserDaoData dao;

    public Iterable<User> getAll() {
        return dao.findAll();
    }

    public Optional<User> getById(long id) {
        return dao.findById(id);
    }

    public User getByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    @Transactional
    public void save(User user) {
        dao.save(user);
    }

    @Transactional
    public void delete(User user) {
        dao.delete(user);
    }

    @Transactional
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        dao.save(user);
    }

    public boolean isEmailFree(String email) {
        return dao.existsUserByEmail(email);
    }

    @Autowired
    public void setDaoData(UserDaoData dao) {
        this.dao = dao;
    }
}
