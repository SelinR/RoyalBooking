package services;

import dao.UserDAO;
import entities.User;
import enums.UserType;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;

public class UserService {
    private UserDAO dao;

    public List<User> getAll() {
        return dao.getAll();
    }

    public void save(HttpServletRequest request) {
        User user = create(request);
        dao.save(user);
    }

    private User create(HttpServletRequest request) {
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
            if (key.equalsIgnoreCase("name")) {
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
       return new User(name, surname, country, birthday, phone, email, userType);
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
}
