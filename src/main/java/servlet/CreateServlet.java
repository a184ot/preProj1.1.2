package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/create", "/create" })
public class CreateServlet extends HttpServlet {
    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("name") == null || request.getParameter("age") == null || request.getParameter("email") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
            dispatcher.forward(request, response);
        } else {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Long age = Long.valueOf(request.getParameter("age"));
            String role = "user";
            User newUser = new User(name, password, age, email, role);
            userServiceImpl.addUser(newUser);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("name") == null || request.getParameter("age") == null || request.getParameter("email") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
            dispatcher.forward(request, response);
        } else {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Long age = Long.valueOf(request.getParameter("age"));
            String role = "user";
            User newUser = new User(name, password, age, email, role);
            userServiceImpl.addUser(newUser);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin");
        dispatcher.forward(request, response);
    }


}
