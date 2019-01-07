package dao.jdbcDaoImpl;

import dao.RoomDAO;
import entities.Room;
import enums.RoomType;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomDAOImpl implements RoomDAO {
    private static JdbcRoomDAOImpl instance;
    private static final String SQL_GET_ALL_ROOMS = "SELECT rooms.id, rooms.room_type, rooms.beds_amount, rooms.area, rooms.daily_cost, rooms.additional_info FROM rooms;";
    private static final String SQL_GET_ROOM_BY_ID = "SELECT rooms.id, rooms.room_type, rooms.beds_amount, rooms.area, rooms.daily_cost, rooms.additional_info FROM rooms WHERE ID = ?;";
    private static final String SQL_SAVE_ROOM = "INSERT INTO rooms (room_type, beds_amount, area, daily_cost, additional_info) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_ROOM = "UPDATE rooms SET roomtype = ?, bedsamount = ?, area = ?, dailycost = ? WHERE id = ?;";
    private static final String SQL_DELETE_ROOM = "DELETE FROM rooms WHERE id = ?;";

    private JdbcRoomDAOImpl() {

    }

    public static JdbcRoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new JdbcRoomDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = DBConnection.openConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ROOMS);
            while (resultSet.next()) {
                Room room = createRoom(resultSet);
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not get list of rooms: " + e);
        }
    }

    @Override
    public Room getById(int id) {
        try (Connection connection = DBConnection.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ROOM_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return createRoom(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not get room by id: " + e);
        }
    }

    @Override
    public void save(Room room) {
        try (Connection connection = DBConnection.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ROOM)) {
            configurePreparedStatement(preparedStatement, room);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not save room: " + e);
        }
    }

    @Override
    public void update(Room room) {
        try (Connection connection = DBConnection.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ROOM)) {
            configurePreparedStatement(preparedStatement, room);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not update room: " + e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ROOM)) {
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not delete room: " + e);
        }
    }

    private Room createRoom(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            RoomType roomType = RoomType.valueOf(resultSet.getString("room_type").replaceAll("'", ""));
            int beds = resultSet.getInt("beds_amount");
            double area = resultSet.getDouble("area");
            double dailyCost = resultSet.getDouble("daily_cost");
            String info = resultSet.getString("additional_info");
            return new Room(id, roomType, beds, area, dailyCost, info);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sorry, could not create room: " + e);
        }
    }

    private void configurePreparedStatement(PreparedStatement preparedStatement, Room room) throws SQLException {
        preparedStatement.setString(1, room.getRoomType().toString());
        preparedStatement.setInt(2, room.getBedsAmount());
        preparedStatement.setDouble(3, room.getArea());
        preparedStatement.setDouble(4, room.getDailyCost());
        preparedStatement.setString(5, room.getAdditionalInfo());
    }
}
