package dao.daoimpl;

import dao.UserDAO;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private List<User> users;

    public UserDAOImpl() {
        users = new ArrayList<User>();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
}
