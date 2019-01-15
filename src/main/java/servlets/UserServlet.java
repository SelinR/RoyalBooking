package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private static final String index = "/WEB-INF/view/users.jsp";
    private UserService service;

    public void init() {
        service = WelcomeServlet.getContext().getBean("userService", UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = service.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher(index).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        service.save(request);
        doGet(request, response);
    }
}
