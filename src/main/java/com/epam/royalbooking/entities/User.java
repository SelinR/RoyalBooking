package com.epam.royalbooking.entities;

import com.epam.royalbooking.enums.UserType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String surname;
    private String country;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    private String phone;
    private String email;
    private UserType userType;

    public User() {

    }

    public User(String name, String surname, String country, LocalDate birthday, String phone, String email, UserType userType) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
    }
    public User(int id, String name, String surname, String country, LocalDate birthday, String phone, String email, UserType userType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
