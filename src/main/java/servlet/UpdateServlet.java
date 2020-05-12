package servlet;

import model.User;
import dao.UserDaoFactory;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    ReadServlet readServlet = new ReadServlet();
    UserDaoFactory userDaoFactory = new UserDaoFactory();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        if (request.getParameter("name") == null) {
            User existingUser = userService.getUserById(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
            request.setAttribute("user", existingUser);
            dispatcher.forward(request, response);
        }
        if (request.getParameter("age") == null || request.getParameter("email") == null) {
            readServlet.listUser(request, response);
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Long age = Long.valueOf(request.getParameter("age"));
        User user = new User(id, name, age, email);
        userService.updateUser(user);
        readServlet.listUser(request,response);
    }


}
