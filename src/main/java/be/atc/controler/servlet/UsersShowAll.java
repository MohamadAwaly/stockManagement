package be.atc.controler.servlet;

import be.atc.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
