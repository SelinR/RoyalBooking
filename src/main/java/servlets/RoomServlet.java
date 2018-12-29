package servlets;

import entities.Room;
import services.RoomService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class RoomServlet extends HttpServlet {
    RoomService service = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String[]> parameters = req.getParameterMap();
        service.getAll(parameters);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset = UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        service.save(id, roomType, bedsAmount, area, dailyCost, additionalInfo);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

    }
}
