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
    private static final Logger logger       = Logger.getLogger( Login.class );


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = "";
        String password = "";
        login = request.getParameter("login-user");
        password = request.getParameter("password");
        logger.log(Level.INFO,"login: " + login);
        logger.log(Level.INFO, "password: " + password );
        String passwordHached = BCrypt.hashpw( password, BCrypt.gensalt() );

        UserService userService = new UserService();
        logger.log(Level.INFO, "response methode: " + userService.checkLogin(login, passwordHached));

        List user = userService.checkLogin(login,password);
        logger.log(Level.INFO, "List: " + user);
        for (Object test:
             user) {
            logger.log(Level.INFO,"test: " + test);
//            logger.log(Level.INFO,"test: " + test.getLogin());
        }

        if (!(userService.checkLogin(login, passwordHached ).equals(null))) {
            logger.log(Level.INFO,"dans le if");
//            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            response.sendRedirect(request.getContextPath() +"/UsersShowAll");
        } else {
            logger.log(Level.INFO,"dans le else");

            this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
        }
    }
}
