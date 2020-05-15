package servlet;

import model.User;
import service.UserService;

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
    UserService userService = UserService.getInstance();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listUser(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void listUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = String.valueOf(request.getSession().getAttribute("login"));
        User user = userService.getUserByName(name);
        List<User> listUser = new ArrayList<>();
        listUser.add(user);

        request.setAttribute("listUser", listUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("userRead.jsp");
        dispatcher.forward(request, response);
    }




}