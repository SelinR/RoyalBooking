package services;

import dao.UserDAO;
import dao.daoimpl.UserDAOImpl;
import entities.User;
import enums.UserType;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

public class UserService {
    private static UserService instance;
    private UserDAO userDAO = UserDAOImpl.getInstance();

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void save(HttpServletRequest request) {
        User user = create(request);
        userDAO.save(user);
    }

    public void update() {

    }

    public void delete() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private User create(HttpServletRequest request) {
        int id = 0;
        String name = null;
        String surname = null;
        String country = null;
        LocalDate birthday = null;
        String phone = null;
        String email = null;
        UserType userType = UserType.USER;

        Enumeration<String> parametersNames = request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String key = parametersNames.nextElement();
            String val = request.getParameter(key);
            if (key.equalsIgnoreCase("id")) {
                id = Integer.parseInt(val);
            } else if (key.equalsIgnoreCase("name")) {
                name = val;
            } else if (key.equalsIgnoreCase("surname")) {
                surname = val;
            } else if (key.equalsIgnoreCase("county")) {
                country = val;
            } else if (key.equalsIgnoreCase("birthday")) {
                birthday = LocalDate.parse(val);
            } else if (key.equalsIgnoreCase("phone")) {
                phone = val;
            } else if (key.equalsIgnoreCase("email")) {
                email = val;
            } else if (key.equalsIgnoreCase("userType")) {
                userType = UserType.valueOf(val);
            }
        }
       return new User(id, name, surname, country, birthday, phone, email, userType);
    }
}
