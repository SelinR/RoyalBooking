package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.UserDAO;
import com.epam.royalbooking.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

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

    public void update(User user) {
        dao.update(user);
    }

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public boolean isSubmitRequestValid(Model model) {
        Map<String, Object> request = model.asMap();
        User user = (User) request.get("user");
        return user.getPassword().equals(user.getPasswordRepeat());
    }
}
