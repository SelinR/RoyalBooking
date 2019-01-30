package com.epam.royalbooking.services;

import com.epam.royalbooking.config.ApplicationConfig;
import com.epam.royalbooking.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
public class UserServiceTest {
    private UserService userService;

    @Test
    public void testIsUserDataValid() {
        User vasiliy = new User("Vasiliy", "Semenov", "Russia", "+79214232512", "semenovVV@mail.ru", LocalDate.of(1999, 2, 15), "password");
        User idealRegEx = new User("Johnathan", "O'Reyli", "Isla de Muerte", "+78008800880", "standard@email.com", LocalDate.of(2015, 11, 1), "justapassworddogo");
        User jeorge = new User("Jeorge", "Bush", "USA", "+12345236612", "presidentBUSHJR@gmail.com", LocalDate.of(2000, 1, 1), "pasd123as");
        User yulia = new User("Yulia", "Kim", "South Korea", "+829431234123", "YKimSempai@southkoreanmail.com", LocalDate.of(-2222, 2, 15), "OhMyGodThisIsSuchACoolPassword123");
        User mysterious = new User("Mysterious", "Stranger", "Secret", "0", "MySterMysterious@mystery.org", LocalDate.of(1, 1, 1), "");

        assertTrue(userService.isUserDataValid(vasiliy));
        assertTrue(userService.isUserDataValid(idealRegEx));
        assertTrue(userService.isUserDataValid(jeorge));
        assertFalse(userService.isUserDataValid(yulia));
        assertFalse(userService.isUserDataValid(mysterious));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
