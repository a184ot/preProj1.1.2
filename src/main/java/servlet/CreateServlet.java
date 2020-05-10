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

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    ReadServlet readServlet;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("name") == null || request.getParameter("age") == null || request.getParameter("email") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(request, response);
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Long age = Long.valueOf(request.getParameter("age"));
        User newUser = new User(name, age, email);
        userService.addUser(newUser);
        readServlet.listUser(request, response);

    }


}
