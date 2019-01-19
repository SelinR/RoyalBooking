package com.epam.royalbooking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String PATH = DBConnection.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
            "\\config.properties";
    private static String url;
    private static String username;
    private static String password;

    static {
        try (FileInputStream fileInputStream = new FileInputStream(PATH)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            url = properties.getProperty("site");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find properties file: " + e);
        }
    }

    public static Connection openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open the DB connection: " + e);
        }
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
