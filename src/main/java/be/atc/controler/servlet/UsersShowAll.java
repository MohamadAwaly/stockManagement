package be.atc.controler.servlet;

import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
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
            System.out.println( "Erreur servlet get" );
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    }
}
