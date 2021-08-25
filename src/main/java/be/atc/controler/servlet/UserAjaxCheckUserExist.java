package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.service.AdressService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserAjaxCheckUserExist", value = "/UserAjaxCheckUserExist")
public class UserAjaxCheckUserExist extends HttpServlet {
    private static Logger logger = Logger.getLogger( UserAjaxCheckUserExist.class );
    EntityManager em = EMF.getEM();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService(em);
        String login = request.getParameter("login");
//        logger.log(Level.INFO, "checkUserAjax: " + login);
        boolean checkLoginExist = userService.checkUserExist(login);
        if (checkLoginExist) {
//            response.getWriter().write("Error - user exist");
            response.getWriter().write("error");
        } else {
            response.getWriter().write("ok");
        }
    }
}
