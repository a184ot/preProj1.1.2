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
import java.util.List;

@WebServlet("")
public class StartServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        //        userService = new UserService();
//        userService = UserService.getInstance().;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        userService.createTable();
        List<User> listUser = UserService.getInstance().getAllUsers();
//        List<User> listUser1 = new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).getAllUsers();
//        List<User> listUser = userService.getAllUsers();

        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}

