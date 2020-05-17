package filter;

import service.UserServiceImpl;

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
        final HttpSession session = req.getSession();
        final String name = String.valueOf(req.getSession().getAttribute("name"));
        final String password = String.valueOf(req.getSession().getAttribute("password"));
        final String role = String.valueOf(req.getSession().getAttribute("role"));
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

        if (!role.equals("admin") && !role.equals("user")) {
            req.setAttribute("role", role);
            RequestDispatcher dispatcher = req.getRequestDispatcher("login");
            dispatcher.forward(req, res);
        } else if (role.equals("user") && (!req.getRequestURI().equals("/user") && !req.getRequestURI().equals("/logout"))) {
//            final String role = userServiceImpl.getUserByName(name).getRole();
            req.setAttribute("role", role);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Error.jsp");
            dispatcher.forward(req, res);
        } else {
            filterChain.doFilter(req, res);;
        }
    }


    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {

        /*if (role.equals("admin")) {
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
        }*/
    }


    @Override
    public void destroy() {
    }
}
