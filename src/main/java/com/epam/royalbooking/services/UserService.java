package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.UserDao;
import com.epam.royalbooking.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserDao dao;

    public List<User> getAll() {
        return createUsersList();
    }

    public User getById(int id) {
        return createUser(id);
    }

    public User getByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    @Transactional
    public void save(User user) {
        dao.save(user);
    }

    @Transactional
    public void delete(int id) {
        dao.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        dao.save(user);
    }

    public boolean isEmailExists(String email) {
        return dao.existsUserByEmail(email);
    }

    private List<User> createUsersList() {
        Iterable<User> userIterable = dao.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        return userList;
    }

    private User createUser(int id) {
        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("No user found with id: " + id);
        }
    }

    @Autowired
    public void setDaoData(UserDao dao) {
        this.dao = dao;
    }
}
