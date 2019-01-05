package dao.jdbcDaoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcRoomDAOImpl {
    private static JdbcRoomDAOImpl instance;
    private final String url = "jdbc:postgresql://localhost:5432/royalbooking";
    private final String username = "postgres";
    private final String password = "postgres";

    private JdbcRoomDAOImpl() {

    }

    public static JdbcRoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new JdbcRoomDAOImpl();
        }
        return instance;
    }

    private boolean getConnection() {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {

        }
    }
}
