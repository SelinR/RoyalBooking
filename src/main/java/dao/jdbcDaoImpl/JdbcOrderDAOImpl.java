package dao.jdbcDaoImpl;

import dao.OrderDAO;
import entities.Order;
import enums.OrderStatus;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrderDAOImpl implements OrderDAO {
    private static JdbcOrderDAOImpl instance;
    private static final String SQL_GET_ALL_ORDERS = "SELECT id,status, booked_room_id, " +
            "entry_date, leave_date, total_price, user_id FROM orders;";
    private static final String SQL_GET_ORDER_BY_ID = "SELECT id,status, booked_room_id, " +
            "entry_date, leave_date, total_price, user_id FROM orders WHERE ID = ?;";
    private static final String SQL_SAVE_ORDER = "INSERT INTO orders(status, booked_room_id," +
            " entry_date, leave_date, total_price, user_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET status = ?, " +
            "booked_room_id = ?, entry_date = ?, leave_date = ?, total_price = ?, user_id = ? WHERE id = ?;";
    private static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id = ?;";

    public static JdbcOrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new JdbcOrderDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBConnection.openConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ORDERS);

            while (resultSet.next()) {
               Order order = createOrder(resultSet);
               orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not get list of orders: " + e);
        }
    }

    @Override
    public Order getById(int id) {
        try (Connection connection = DBConnection.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ORDER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return createOrder(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not get order by id: " + e);
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = DBConnection.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ORDER)) {
            configurePreparedStatement(preparedStatement, order);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not save order: " + e);
        }
    }

    @Override
    public void update(Order order){
        try (Connection connection = DBConnection.openConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER)){
            configurePreparedStatement(preparedStatement, order);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not update order");
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DBConnection.openConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER)) {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not delete order");
        }

    }

    private Order createOrder(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            OrderStatus status = OrderStatus.valueOf(resultSet.getString("status").replaceAll("'", ""));
            int bookedRoomId = resultSet.getInt("booked_room_id");
            LocalDate entryDate = LocalDate.parse(resultSet.getString("entry_date"));
            LocalDate leaveDate = LocalDate.parse(resultSet.getString("leave_date"));
            double totalPrice = resultSet.getDouble("total_price");
            int userId = resultSet.getInt("user_id");
            return new Order(id,bookedRoomId, entryDate, leaveDate, totalPrice,userId, status);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not create order: " + e);
        }
    }

    private void configurePreparedStatement(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setString(1,order.getStatus().toString());
        preparedStatement.setInt(2, order.getBookedRoomID());
        preparedStatement.setDate(3, Date.valueOf(order.getEntryDate()));
        preparedStatement.setDate(4, Date.valueOf(order.getLeaveDate()));
        preparedStatement.setDouble(5, order.getTotalPrice());
        preparedStatement.setInt(6, order.getUserID());
    }
}
