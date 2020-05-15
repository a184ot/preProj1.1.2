package filter;

import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final HttpSession session = req.getSession();
        UserService userService = UserService.getInstance();

        if (session.getAttribute("login") != null && session.getAttribute("password") != null) {
            final String role = String.valueOf(session.getAttribute("role"));
            moveToMenu(req, res, role);
        } else if (userService.isUserExist(login, password)) {
            final String role = userService.getUserByName(login).getRole();
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, "");
        }
    }


    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {

        if (role.equals("admin")) {
            String ur = req.getRequestURI();


            if (ur.equals("/login")){
                RequestDispatcher dispatcher = req.getRequestDispatcher("admin");
                dispatcher.forward(req, res);
            }
//
            RequestDispatcher dispatcher = req.getRequestDispatcher(ur);
            dispatcher.forward(req, res);
        } else if (role.equals("user")) {
            String ur = req.getRequestURI();
            String admin = "";
            if (!ur.equals("/logout")) {
                ur = "user";
            }
            String path = admin + ur;
            RequestDispatcher dispatcher = req.getRequestDispatcher(path);
            dispatcher.forward(req, res);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
            dispatcher.forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }
}
