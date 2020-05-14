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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    ReadServlet readServlet = new ReadServlet();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("name") != null && request.getParameter("password") != null) {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            User user = userService.getUserByName(name);
//            Long id = Long.valueOf(request.getParameter("id"));
//            User user = userService.getUserById(id);
            if (user.getName().equals(name) && password.equals("123456")){

              listUser(request, response);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("read.jsp");
//            dispatcher.forward(request, response);
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
            dispatcher.forward(request, response);
        }
        listUser(request, response);
    }

    protected void listUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
//        List<User> listUser = userService.getAllUsers();
//        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin");
        dispatcher.forward(request, response);
    }
}
