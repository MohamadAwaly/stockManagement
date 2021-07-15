package be.atc.controler.servlet;

import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.*;
import be.atc.service.CitieService;
import be.atc.service.RoleService;
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
    public static final  String       VUE          = "/views/updateUser.jsp";
    private static final Logger       logger       = Logger.getLogger( UserUpdate.class );
    private              UserService  userService  = new UserService();
    private              RoleService  roleService  = new RoleService();
    private              CitieService citieService = new CitieService();
    List<RolesEntity>  roleList      = roleService.showAllRoles();
    List<CitiesEntity> citiesList    = citieService.showAllCities();
    TypeAdress[]       allTypeAdress = TypeAdress.values();

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        UserService userService = new UserService();
        List<Object[]> user = userService
                .selectUserById( Integer.parseInt( request.getParameter( "selectedUser-id" ) ) );
        logger.log( Level.INFO, "ID : " + Integer.parseInt( request.getParameter( "selectedUser-id" ) ) );
        //cities
        try {
            request.setAttribute( "user", user );
            request.setAttribute( "roles", roleList );
            request.setAttribute( "cities", citiesList );
            request.setAttribute( "allTypeAdress", allTypeAdress );
        } catch ( Exception e ) {
            logger.log( Level.ERROR, "User error" + e.getMessage() );
        }

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }
}
