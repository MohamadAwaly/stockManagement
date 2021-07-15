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

@WebServlet( name = "userUpdate", value = "/userUpdate" )
public class UserUpdate extends HttpServlet {
    public static final  String      VUE    = "/views/updateUser.jsp";
    private static final Logger logger = Logger.getLogger( UserUpdate.class );
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        logger.log(Level.INFO, "Servlet userUpdate1");
        UserService userService = new UserService();
        logger.log(Level.INFO, "Servlet userUpdate21");

//        List<Object[]> user = userService.selectUserById( Integer.parseInt( request.getParameter( "selectedUserLogin" ) ) );
//        logger.log(Level.INFO, "Servlet userUpdate3");
//        for (Object usertest : user) {
//            logger.log(Level.INFO,"User: " + usertest);
//        }
//
//
//        try {
//            request.setAttribute( "user", user );
//        } catch ( Exception e ) {
//            logger.log(Level.ERROR, "User error" + e.getMessage());
//        }

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );


    }
}
