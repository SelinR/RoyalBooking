package dao.jdbcDaoImpl;

import dao.RoomDAO;
import entities.Room;
import enums.RoomType;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcRoomDAOImpl implements RoomDAO {
    private static JdbcRoomDAOImpl instance;
    private static final String path = JdbcRoomDAOImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
            "\\config.properties";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private JdbcRoomDAOImpl() {
        openConnection();
    }

    public static JdbcRoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new JdbcRoomDAOImpl();
        }
        return instance;
    }

    private void openConnection() {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String url = properties.getProperty("site");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * resultSet is automatically closed when statement instance is closed
     */
    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM public.rooms");
            while (resultSet.next()) {
                Room room = createRoom(resultSet);
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Room getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT rooms.id, rooms.room_type," +
                    "rooms.beds_amount, rooms.area, rooms.daily_cost, rooms.additional_info FROM public.rooms WHERE ID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return createRoom(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Sorry, some error has occurred.");
    }

    @Override
    public void save(Room room) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO public.rooms (room_type, beds_amount, area, daily_cost, additional_info) VALUES \n" +
                            "(?, ?, ?, ?, ?);");
            preparedStatement.setString(1, room.getRoomType().toString());
            preparedStatement.setInt(2, room.getBedsAmount());
            preparedStatement.setDouble(3, room.getArea());
            preparedStatement.setDouble(4, room.getDailyCost());
            preparedStatement.setString(5, room.getAdditionalInfo());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Room room) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE public.rooms\n" +
                            "SET roomtype = " + "'" + room.getRoomType() + "'" +
                            ", bedsamount = " + room.getBedsAmount() +
                            ", area = " + room.getArea() +
                            ", dailycost = " + room.getDailyCost() +
                            ", additionalinfo = " + "'" + room.getAdditionalInfo() + "'" +
                            "\n WHERE id = " + room.getId() + ";");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.rooms WHERE id = ?;");
            preparedStatement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
        throw new RuntimeException("Sorry, some error has occurred.");
    }
}
