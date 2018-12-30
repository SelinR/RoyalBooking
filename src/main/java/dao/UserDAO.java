package dao;

import entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getById(int id);
    void save(User user);
    void delete(int id);
}
