package servlets;

import entities.Room;
import services.RoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoomServlet extends HttpServlet {
    private static final String index = "/view/rooms.jsp";
    private final RoomService service = RoomService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Room> rooms = service.getAll();
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset = UTF-8");
        req.setCharacterEncoding("UTF-8");
        if (!isRequestValid(req)) {
            doGet(req, resp);
        }
        service.save(req);
        doGet(req, resp);
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
        int bedsAmount = 0;
        double area = 0;
        double dailyCost = 0;
        try {
            bedsAmount = Integer.parseInt(req.getParameter("bedsAmount"));
            area = Double.parseDouble(req.getParameter("area"));
            dailyCost = Double.parseDouble(req.getParameter("dailyCost"));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
