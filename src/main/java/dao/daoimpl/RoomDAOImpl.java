package dao.daoimpl;

import dao.RoomDAO;
import entities.Room;
import enums.RoomType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RoomDAOImpl implements RoomDAO {
    private static RoomDAOImpl instance;
    private List<Room> rooms;

    private RoomDAOImpl() {
        rooms = new ArrayList<>();
        fillRooms();
    }

    public static RoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new RoomDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }

    @Override
    public Room getById(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        throw new IllegalArgumentException("No room found with id: " + id);
    }

    /**
     * In this implementation it does not matter what ID do we set to the {@param room} in the constructor
     * when creating it, id is roughly reset to the (id_of_a_last_room_in_the_list + 1).
     * It is a temporal decision. Remove when database will be implemented.
     * @param room  room to be saved.
     */
    @Override
    public void save(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Please, check your input.");
        }
        if (rooms.size() != 0) {
            int idOfLastRoom = rooms.get(rooms.size() - 1).getId();
            room.setId(idOfLastRoom + 1);
        } else {
            room.setId(0);
        }
        rooms.add(room);
    }

    @Override
    public void update(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Please, check your input.");
        }
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId() == room.getId()) {
                rooms.set(i, room);
                return;
            }
        }
        throw new IllegalArgumentException("No room found with id: " + room.getId());
    }

    @Override
    public void delete(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                rooms.remove(id);
                return;
            }
        }
        throw new IllegalArgumentException("No room found with id: " + id);
    }

    private void fillRooms() {
        final List<RoomType> values = Collections.unmodifiableList(Arrays.asList(RoomType.values()));
        final int size = values.size();
        final Random random = new Random();

        for (int i = 0; i < 5; i++) {
            RoomType randomRoomType = values.get(random.nextInt(size));
            Room room = new Room(i, randomRoomType, i, i, i, "info");
            rooms.add(room);
        }
    }
}
