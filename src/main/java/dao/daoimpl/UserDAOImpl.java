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

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public void save(User user) {
        user.setId(users.size());
        users.add(user);
    }

    @Override
    public void update(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
            }
        }
    }

        @Override
        public void delete ( int id){
            users.remove(id);
        }

        public static UserDAOImpl getInstance () {
            return instance;
        }

    }

