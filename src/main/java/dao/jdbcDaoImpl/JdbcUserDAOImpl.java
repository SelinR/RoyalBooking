package dao.jdbcDaoImpl;

import dao.UserDAO;
import entities.User;
import enums.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDAOImpl implements UserDAO {
    public static JdbcUserDAOImpl instance;
    private Connection connection;

    private JdbcUserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Temporary decision.
     * @return
     */
    public static JdbcUserDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new JdbcUserDAOImpl(DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1"));
            } catch (SQLException e) {
                throw new RuntimeException("Something went wrong");
            }
        }
        return instance;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QueryType.GET_ALL.getQuery())) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("getAll method failed.");
        }
        return users;
    }

    @Override
    public User getById(int id) {
        User result;
        try (PreparedStatement statement = connection.prepareStatement(QueryType.GET_BY_ID.getQuery())) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = createUser(resultSet);
            } else {
                throw new RuntimeException("There is no such user.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("getById method failed.");
        }
        return result;
    }

    @Override
    public void save(User user) {
        try (PreparedStatement statement = connection.prepareStatement(QueryType.SAVE.getQuery())) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getCountry());
            statement.setDate(4, Date.valueOf(user.getBirthday()));
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getUserType().toString());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("save method failed.");
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(QueryType.DELETE.getQuery())) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("delete method failed");
        }
    }

    private User createUser(ResultSet resultSet) {
        try {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("country"),
                    resultSet.getDate("birthday").toLocalDate(),
                    resultSet.getString("phone"),
                    resultSet.getString("email"),
                    UserType.valueOf(resultSet.getString("email").replaceAll("'", ""))
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong with user creation!");
        }
    }

    enum QueryType {
        GET_ALL("SELECT id, name, surname, country, birthday, phone, email, user_type FROM users"),
        GET_BY_ID("SELECT * FROM users WHERE id = (?)"),
        SAVE("INSERT INTO users (id, name, surname, country, birthday, phone, email, user_type) " +
                "VALUES (DEFAULT, (?), (?), (?), (?), (?), (?), (?))"),
        UPDATE("UPDATE users SET name = (?), surname = (?), county = (?), birthday = (?), phone = (?), email = (?), " +
                "user_type = (?) WHERE id = (?)"),
        DELETE("DELETE FROM users WHERE id = (?)");

        private String query;

        QueryType(String query) {
            this.query = query;
        }

        public String getQuery() {
            return query;
        }
    }
}