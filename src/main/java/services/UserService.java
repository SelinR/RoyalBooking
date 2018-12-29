package services;

import dao.UserDAO;
import entities.User;
import enums.UserType;

import java.time.LocalDate;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public List<User> getAll() {

    }

    public void save() {

    }

    public void update() {

    }

    public void delete() {

    }

    private User create(int id, String name, String surname, String country, LocalDate birthday, String phone, String email, UserType userType) {
       return new User(id, name, surname, country, birthday, phone, email, userType);
    }
}
