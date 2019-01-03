package dao.daoimpl;

import dao.UserDAO;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private List<User> users;

    private static UserDAOImpl instance = new UserDAOImpl();

    private UserDAOImpl() {
        users = new ArrayList<User>();
    }

    public List<User> getAll() {
        return users;
    }

    public User getById(int id) {
        return users.get(id);
    }

    public static UserDAOImpl getInstance() {
        return instance;
    }

    public void save(User user) {
        user.setId(users.size());
        users.add(user);
    }

    public void delete(int id) {
        users.remove(id);
    }
}
