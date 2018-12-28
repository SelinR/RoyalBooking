package dao;

import entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    void saveUser(User user);
    void deleteUser(User user);
}
