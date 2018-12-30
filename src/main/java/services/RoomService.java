package services;

import dao.daoimpl.RoomDAOImpl;
import entities.Room;
import enums.RoomType;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class RoomService {
    private final RoomDAOImpl dao = RoomDAOImpl.getInstance();

    public List<Room> getAll() {
        return dao.getAll();
    }

    public void save(HttpServletRequest req) {
        Room room = create(req);
        dao.save(room);
    }

    public void update() {

    }

    public void delete() {

    }

    private Room create(HttpServletRequest req) {
        if (req == null) {
            throw new RuntimeException("No data found. Please, specify a query.");
        }
        int id = 0;
        RoomType roomType = null;
        int bedsAmount = 0;
        double area = 0;
        double dailyCost = 0;
        String additionalInfo = null;

        Enumeration<String> parametersNames = req.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String key = parametersNames.nextElement();
            String val = req.getParameter(key);
            if (key.equalsIgnoreCase("id")) {
                id = Integer.parseInt(val);
            } else if (key.equalsIgnoreCase("roomType")) {
                roomType = RoomType.valueOf(val);
            } else if (key.equalsIgnoreCase("bedsAmount")) {
                bedsAmount = Integer.parseInt("bedsAmount");
            } else if (key.equalsIgnoreCase("area")) {
                area = Double.parseDouble("area");
            } else if (key.equalsIgnoreCase("dailyCost")) {
                dailyCost = Double.parseDouble(val);
            } else if (key.equalsIgnoreCase("additionalInfo")) {
                additionalInfo = val;
            } else {
                throw new RuntimeException("Something's gone bad, call your system administrator");
            }
        }
        return new Room(id, roomType, bedsAmount, area, dailyCost, additionalInfo);
    }
}
