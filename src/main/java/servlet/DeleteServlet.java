package servlet;

import service.UserDaoFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    ReadServlet readServlet = new ReadServlet();
    UserDaoFactory userDaoFactory = new UserDaoFactory();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            userDaoFactory.deleteUser(id);
        } else {
            userDaoFactory.dropTable();
            userDaoFactory.createTable();
        }
        readServlet.listUser(request, response);
    }
}
