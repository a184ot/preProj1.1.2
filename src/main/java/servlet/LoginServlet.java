package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"", "/login"})
public class LoginServlet extends HttpServlet {
    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
    public void init() {
        User user = new User("admin","123456", 21L, "ee@ee.ee", "admin");
        userServiceImpl.addUser(user);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("name") == null && request.getParameter("password") == null) {
            errorEnter(request, response);
        }
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (!userServiceImpl.isUserExist(name, password)) {
            errorEnter(request, response);
        }
        User user = userServiceImpl.getUserByName(name);
        HttpSession session = request.getSession();
        final String role = user.getRole();
        session.setAttribute("password", password);
        session.setAttribute("name", name);
        session.setAttribute("role", role);
        if (role.equals("admin")) {
            adminEnter(request, response);
        }else {
            userEnter(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }

    private void adminEnter(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin");
        dispatcher.forward(request, response);
    }
    private void userEnter(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user");
        dispatcher.forward(request, response);
    }

    private void errorEnter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
        dispatcher.forward(request, response);
    }
}
