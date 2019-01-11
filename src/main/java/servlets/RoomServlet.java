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
    private static final String index = "/WEB-INF/view/rooms.jsp";
    private static final String error = "/WEB-INF/view/ErrorPage.html";
    private RoomService service;

    @Override
    public void init() throws ServletException {
        service = WelcomeServlet.getContext().getBean("roomService", RoomService.class);
    }

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
            req.getRequestDispatcher(error).forward(req, resp);
        } else {
            doGet(req, resp);
        }
    }
}
