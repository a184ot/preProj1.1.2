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
import java.util.*;

@WebServlet("/user")
public class UserReadServlet extends HttpServlet {
    UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listUser(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        listUser(request, response);
    }

    protected void listUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = String.valueOf(request.getSession().getAttribute("name"));
        User user = userServiceImpl.getUserByName(name);
        List<User> listUser = new ArrayList<>();
        listUser.add(user);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        request.setAttribute("role", role);
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userRead.jsp");
        dispatcher.forward(request, response);
    }




}