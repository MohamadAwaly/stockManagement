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
        HttpSession session = request.getSession();
        login = request.getParameter("login-user");
        password = request.getParameter("password");
//        String passwordHached = BCrypt.hashpw( password, BCrypt.gensalt() );
        UserService userService = new UserService();
//        List <Object> user = userService.checkLogin(login);
        UsersEntity user = userService.checkLogin(login);
        Boolean checkpw = false;
//        logger.log(Level.INFO,"Taille de la liste: " + user.size());
        String error = "Erreur! Username/password incorrect ";
//        for (Object str: user) {
            checkpw =  BCrypt.checkpw(password,user.getPassword());
//            logger.log(Level.INFO,"User checked login is: " + checkpw);
//            logger.log(Level.INFO,"i : " + str);
//        }

        logger.log(Level.INFO, "CheckUser dans la servlet: " + user.getLogin());


        if (checkpw) {
            response.sendRedirect(request.getContextPath() +"/UsersShowAll");
            session.setAttribute("SessionUser", login);
        } else {
            session.setAttribute("SessionUser", null);
            request.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
        }
    }
}
