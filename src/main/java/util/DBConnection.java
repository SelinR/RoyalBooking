package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String path = DBConnection.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
            "\\config.properties";

    public Connection openConnection() {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String url = properties.getProperty("site");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open the DB connection: " + e);
        }
    }
}
