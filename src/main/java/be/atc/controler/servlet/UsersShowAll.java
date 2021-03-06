package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet( name = "usersshowall", value = "/UsersShowAll" )
public class UsersShowAll extends HttpServlet {
    private              UserService user   = new UserService();
    public static final  String      VUE    = "/views/showUsers.jsp";
    private static final Logger      logger = Logger.getLogger( UsersShowAll.class );

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        List<Object[]> users = user.showAllUsers();
        //        HttpSession session = request.getSession();
        try {
            request.setAttribute( "user", users );
        } catch ( Exception e ) {
            logger.log( Level.ERROR, "Error: " + e.getMessage() );
        } finally {
            //            em.close();
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    }
}
