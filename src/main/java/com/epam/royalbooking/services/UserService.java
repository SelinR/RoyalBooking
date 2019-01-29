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
    //private Pattern nameRegEx;
    private Pattern countryRegEx;
    private Pattern birthdayRegEx;
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

    /**
     * userregex.name=^[A-Z][a-z'\.-]{1,15}
     *  Means first letter is uppercase "A-Z", then up to 15 letters "a-zA-Z", "." or "-"
     *
     * userregex.country=[A-Z]{1,1}[ a-zA-Z]{1,29}
     *  Same but with possible whitespace. Issue: multiple whitespaces possible.
     *
     * userregex.email=^(([0-9A-Za-z]{1}[-0-9A-z.]{1,}[0-9A-Za-z]{1})@([-A-Za-z]{1,}.){1,2}[-A-Za-z]{2,6})
     *  Checks for correct e-mail syntax and allows only "\\w", "-" and "." symbols in between
     *
     * userregex.birthday=[\\d]{4,4}-[0-1]?[0-9]?-[0-3]?[0-9]?    (yyyy-mm-dd)
     *  Simple date check so that year cannot be more than 4 digits length.
     *
     * userregex.phone=(([\\+]?[\\d]?)[\\d]{10,10})|[\\d]?[\\d]{10,10}
     *  Allows only (+0123456789) or (01234567890) formats.
     *
     * userregex.password=[\\w]{8,20}
     *  Any "\\w" characters, from 8 to 20
     * @param user user registration data
     * @return  result of a validation check
     */
    public boolean isUserDataValid(User user) {
        boolean name = Pattern.matches("[A-Z]{1,1}[a-zA-Z'.-]{1,15}", user.getName());
        boolean surname = Pattern.matches("[A-Z]{1,1}[a-zA-Z'.-]{1,15}", user.getSurname());
        boolean country = countryRegEx.matcher(user.getCountry()).matches();
        boolean birthday = birthdayRegEx.matcher(user.getBirthday().toString()).matches();
        boolean email = emailRegEx.matcher(user.getEmail()).matches();
        boolean phone = phoneRegEx.matcher(user.getPhone()).matches();
        boolean password = passwordRegEx.matcher(user.getPassword()).matches();
        return name && surname && country && birthday && email && phone && password;
    }

    /**
     * I have encountered some weird bug here: compiler doesn't compile this regex, although it's almost
     * identical to others. Had to move it to the method.
     * Leaving it here to check it later sometime.
     */
    /*@Value("#{T(java.util.regex.Pattern).compile('${userregex.name}')}")
    public void setNameRegEx(Pattern nameRegEx) {
        this.nameRegEx = nameRegEx;
    }*/

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.country}')}")
    public void setCountryRegEx(Pattern countryRegEx) {
        this.countryRegEx = countryRegEx;
    }

    @Value("#{T(java.util.regex.Pattern).compile('${userregex.birthday}')}")
    public void setBirthdayRegEx(Pattern birthdayRegEx) {
        this.birthdayRegEx = birthdayRegEx;
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
