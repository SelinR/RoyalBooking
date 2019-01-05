package dao.jdbcDaoImpl;

import dao.RoomDAO;
import entities.Room;
import enums.RoomType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomDAOImpl implements RoomDAO {
    private static JdbcRoomDAOImpl instance;
    private final String url = "jdbc:postgresql://localhost:5432/royalbooking";
    private final String username = "postgres";
    private final String password = "pass";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private JdbcRoomDAOImpl() {
        openConnection();
        createRoomsTable();
    }

    public static JdbcRoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new JdbcRoomDAOImpl();
        }
        return instance;
    }

    public void openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT rooms.id, rooms.roomtype," +
                    "rooms.bedsamount, rooms.area, rooms.dailycost, rooms.additionalinfo FROM public.rooms WHERE ID = ?");
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
            int id = getIdOfTheLastRoom();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO rooms (id, roomtype, bedsamount, area, dailycost, additionalinfo) VALUES \n" +
                            "(?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "'" + room.getRoomType() + "'");
            preparedStatement.setInt(3, room.getBedsAmount());
            preparedStatement.setDouble(4, room.getArea());
            preparedStatement.setDouble(5, room.getDailyCost());
            preparedStatement.setString(6, room.getAdditionalInfo());
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

    /**
     * Creates a "rooms" table in a DB if one doesn't already exist.
     */
    private void createRoomsTable() {
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS public.rooms ( \n \"id\" integer, \"roomtype\" character varying(10)," +
                    "\"bedsamount\" integer, \"area\" real, \"dailycost\" real, \"additionalinfo\" character varying(50), \n" +
                    "CONSTRAINT \"primary\" PRIMARY KEY (id) ) \n WITH (OIDS = FALSE);";
            statement.execute(query);
            int result = statement.getUpdateCount();
            if (result == 0) {
                fillRooms();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillRooms() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO rooms (id, roomtype, bedsamount, area, dailycost, additionalinfo) VALUES\n" +
                    "(1, 'BASIC', 1, 15.0, 15.0, 'Room #1'),\n" +
                    "(2, 'FAMILY', 3, 20.0, 25.0, 'Room #2'),\n" +
                    "(3, 'LUXURY', 2, 30, 30.0, 'Room #3'),\n" +
                    "(4, 'PENTHOUSE', 5, 50.0, 50.0, 'Room #4');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Room createRoom(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            RoomType roomType = RoomType.valueOf(resultSet.getString("roomtype").replaceAll("'", ""));
            int beds = resultSet.getInt("bedsamount");
            double area = resultSet.getDouble("area");
            double dailyCost = resultSet.getDouble("dailycost");
            String info = resultSet.getString("additionalinfo");
            return new Room(id, roomType, beds, area, dailyCost, info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Sorry, some error has occurred.");
    }
}
