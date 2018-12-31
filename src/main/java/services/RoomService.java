package services;

import dao.daoimpl.RoomDAOImpl;
import entities.Room;
import enums.RoomType;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

public class RoomService {
    private static RoomService instance;
    private final RoomDAOImpl dao = RoomDAOImpl.getInstance();
    private final String numberRegex = "^[0-9]*[.,]?[0-9]+$";

    private RoomService() {

    }

    public static RoomService getInstance() {
        if (instance == null) {
            instance = new RoomService();
        }
        return instance;
    }

    public List<Room> getAll() {
        return dao.getAll();
    }

    public boolean save(HttpServletRequest req) {
        if (!isRequestValid(req)) {
            return false;
        }
        Room room = create(req);
        dao.save(room);
        return true;
    }

    private boolean isRequestValid(HttpServletRequest req) {
        if (req == null) {
            return false;
        }
        String roomType = req.getParameter("roomType");
        String additionalInfo = req.getParameter("additionalInfo");
        if (roomType == null || additionalInfo == null ||
                !(roomType.equalsIgnoreCase("basic") ||
                        roomType.equalsIgnoreCase("family") ||
                        roomType.equalsIgnoreCase("luxury") ||
                        roomType.equalsIgnoreCase("penthouse"))) {
            return false;
        }
        int bedsAmount = -1;
        double area = -1;
        double dailyCost = -1;
        String bedsParameter = req.getParameter("bedsAmount");
        String areaParameter = req.getParameter("area");
        String dailyCostParameter = req.getParameter("dailyCost");
        if (!(Pattern.matches(numberRegex, bedsParameter) ||
                Pattern.matches(numberRegex, areaParameter) ||
                Pattern.matches(numberRegex, dailyCostParameter))) {
            return false;
        }
        try {
            bedsAmount = Integer.parseInt(bedsParameter);
            area = Double.parseDouble(areaParameter);
            dailyCost = Double.parseDouble(dailyCostParameter);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        if (bedsAmount < 0 || area < 0 || dailyCost < 0) {
            return false;
        }
        return true;
    }

    private Room create(HttpServletRequest req) {
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
                bedsAmount = Integer.parseInt(val);
            } else if (key.equalsIgnoreCase("area")) {
                area = Double.parseDouble(val);
            } else if (key.equalsIgnoreCase("dailyCost")) {
                dailyCost = Double.parseDouble(val);
            } else if (key.equalsIgnoreCase("additionalInfo")) {
                additionalInfo = val;
            }
        }
        return new Room(id, roomType, bedsAmount, area, dailyCost, additionalInfo);
    }
}
