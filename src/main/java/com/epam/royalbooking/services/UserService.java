package com.epam.royalbooking.services;

import com.epam.royalbooking.dao.UserDao;
import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private UserDao dao;
    private PasswordEncoder encoder;
    private Pattern nameRegEx;
    private Pattern countryRegEx;
    private Pattern emailRegEx;
    private Pattern phoneRegEx;
    private Pattern passwordRegEx;

    public List<User> getAll() {
        return dao.findAll();
    }

    public User getById(int id) {
        return createUser(id);
    }

    public User getByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    @Transactional
    public void save(User user) {
        user.setUserType(UserType.USER);
        String encryptedUserPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedUserPassword);
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

    private User createUser(int id) {
        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("No user found with id: " + id);
        }
    }

    public boolean isUserDataValid(User user) {
        boolean name = nameRegEx.matcher(user.getName()).matches();
        boolean surname = nameRegEx.matcher(user.getSurname()).matches();
        boolean country = countryRegEx.matcher(user.getCountry()).matches();
        boolean email = emailRegEx.matcher(user.getEmail()).matches();
        boolean phone = phoneRegEx.matcher(user.getPhone()).matches();
        boolean password = passwordRegEx.matcher(user.getPassword()).matches();
        return name && surname && country && email && phone && password;
    }

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.name}')}")
    public void setNameRegEx(Pattern nameRegEx) {
        this.nameRegEx = nameRegEx;
    }

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.country}')}")
    public void setCountryRegEx(Pattern countryRegEx) {
        this.countryRegEx = countryRegEx;
    }

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.email}')}")
    public void setEmailRegEx(Pattern emailRegEx) {
        this.emailRegEx = emailRegEx;
    }

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.phone}')}")
    public void setPhoneRegEx(Pattern phoneRegEx) {
        this.phoneRegEx = phoneRegEx;
    }

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.password}')}")
    public void setPasswordRegEx(Pattern passwordRegEx) {
        this.passwordRegEx = passwordRegEx;
    }

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
