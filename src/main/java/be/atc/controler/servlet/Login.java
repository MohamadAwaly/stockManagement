package be.atc.controler.servlet;

import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    public static final String VUE = "/UsersShowAll";
    public static final String VUE_LOGIN = "/views/login.jsp";
    private static final Logger logger = Logger.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String login = request.getParameter("login-user");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        UsersEntity user = userService.checkLogin(login);
        String error = "Erreur! Username/password incorrect ";
        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        if (checkpw) {
            response.sendRedirect(request.getContextPath() + VUE);
            session.setAttribute("SessionUser", login);
        } else {
            session.setAttribute("SessionUser", null);
            request.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
        }
    }
}
