package com.epam.royalbooking.dao.jdbcDaoImpl;

import com.epam.royalbooking.dao.UserDAO;
import com.epam.royalbooking.entities.User;
import com.epam.royalbooking.enums.UserType;
import com.epam.royalbooking.util.DBConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnection.openConnection(); PreparedStatement statement = connection.prepareStatement(QueryType.GET_ALL.getQuery())) {
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
        try (Connection connection = DBConnection.openConnection();PreparedStatement statement = connection.prepareStatement(QueryType.GET_BY_ID.getQuery())) {
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
        try (Connection connection = DBConnection.openConnection();PreparedStatement statement = connection.prepareStatement(QueryType.SAVE.getQuery())) {
            configurePreparedStatement(statement, user);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("save method failed.");
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = DBConnection.openConnection();PreparedStatement statement = connection.prepareStatement(QueryType.UPDATE.getQuery())) {
            configurePreparedStatement(statement, user);
            statement.setInt(8, user.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("update method failed");
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.openConnection();PreparedStatement statement = connection.prepareStatement(QueryType.DELETE.getQuery())) {
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
                    UserType.valueOf(resultSet.getString("user_type").replaceAll("'", ""))
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong with user creation!");
        }
    }

    private void configurePreparedStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getCountry());
        statement.setDate(4, Date.valueOf(user.getBirthday()));
        statement.setString(5, user.getPhone());
        statement.setString(6, user.getEmail());
        statement.setString(7, user.getUserType().toString());
    }

    enum QueryType {
        GET_ALL("SELECT id, name, surname, country, birthday, phone, email, user_type FROM users"),
        GET_BY_ID("SELECT id, name, surname, country, birthday, phone, email, user_type FROM users WHERE id = (?)"),
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