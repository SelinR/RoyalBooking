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
    private static List<Room> rooms;

    public RoomDAOImpl() {
        if (rooms == null) {
            rooms = new ArrayList<>();
            TempUtilRoomRandomizer.fillRooms();
        }
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }

    @Override
    public Room getById(int id) {
        return rooms.get(id);
    }

    /**
     * In this implementation it does not matter what ID do we set to the {@param room} in the constructor
     * when creating it, id is roughly reset to the (id_of_the_last_room_in_the_list + 1).
     * It is a temporal decision. Remove when database will be implemented.
     * @param room  room to be saved.
     */
    @Override
    public void save(Room room) {
        int lastRoomInListId = rooms.get(rooms.size() - 1).getId();
        room.setId(lastRoomInListId + 1);
        rooms.add(room);
    }

    @Override
    public void update(Room room) {
        rooms.set(room.getId(), room);
    }

    @Override
    public void delete(int id) {
        rooms.remove(id);
    }

    /**
     * This nested class is used to choose a random RoomType when creating a Room instance for filling rooms list.
     * Absolutely temporal, should be removed with rooms list field when database will be added.
     */
    private static class TempUtilRoomRandomizer {
        private static final List<RoomType> VALUES = Collections.unmodifiableList(Arrays.asList(RoomType.values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        private static RoomType randomRoomType() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

        private static void fillRooms() {
            for (int i = 0; i < 10; i++) {
                Room room = new Room(i, randomRoomType(), i, i, i, "info");
                rooms.add(room);
            }
        }
    }
}
