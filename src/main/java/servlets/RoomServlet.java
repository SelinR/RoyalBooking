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
    private static final String error = "ErrorPage.html";
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
        if (!service.save(req)) {
            resp.sendRedirect(error);
        } else {
            doGet(req, resp);
        }
    }
}
