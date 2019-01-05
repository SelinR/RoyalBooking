package servlets;

import entities.Order;
import entities.Room;
import services.OrderService;
import services.RoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends HttpServlet {
    private static final String url = "/WEB-INF/view/orders.jsp";
    private static final String error = "/WEB-INF/view/ErrorPage.html";
    private final OrderService orderService = OrderService.getInstance();
    private final RoomService roomService = RoomService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Order> orders = orderService.getAll();
        List<Room> rooms = roomService.getAll();
        req.setAttribute("orders", orders);
        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher(url).forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset = UTF-8");
        req.setCharacterEncoding("UTF-8");
        Order order = orderService.create(req);
        if (orderService.isOrderValid(order)){
            orderService.save(order);
            doGet(req,resp);
        }
        else {
            resp.sendRedirect(error);
        }
    }
}
