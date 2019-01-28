package com.epam.royalbooking.services;

import com.epam.royalbooking.config.ApplicationConfig;
import com.epam.royalbooking.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class UserServiceTest {
    private UserService userService;

    @Test
    public void testIsUserDataValid() {
        User vasiliy = new User("Vasiliy", "Semenov", "Russia", "+79214232512", "semenovVV@mail.ru", "password");
        User jeorge = new User("Jeorge", "Bush", "USA", "+12345236612", "presidentBUSHJR@gmail.com", "pasd123as");
        User yulia = new User("Yulia", "Kim", "South Korea", "+829431234123", "YKimSempai@southkoreanmail.com", "OhMyGodThisIsSuchACoolPassword123");
        User mysterious = new User("Mysterious", "Stranger", "Secret", "0", "MySterMysterious@mystery.org", "");

        assertTrue(userService.isUserDataValid(vasiliy));
        assertTrue(userService.isUserDataValid(jeorge));
        assertTrue(userService.isUserDataValid(yulia));
        assertFalse(userService.isUserDataValid(mysterious));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
