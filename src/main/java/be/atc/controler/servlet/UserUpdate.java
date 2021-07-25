package be.atc.controler.servlet;

import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.*;
import be.atc.service.AdressService;
import be.atc.service.CitieService;
import be.atc.service.RoleService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "userUpdate", value = "/userUpdate" )
public class UserUpdate extends HttpServlet {
    public static final  String       VUE          = "/views/updateUser.jsp";
    public static final  String       VUE_LISTUSER = "/views/showUsers.jsp";
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
        UserService userService = new UserService();
        AdressService adressService = new AdressService();
        List<Object[]> user = userService
                .selectUserById( Integer.parseInt( request.getParameter( "selectedUser-id" ) ) );
        logger.log( Level.INFO, "ID : " + Integer.parseInt( request.getParameter( "selectedUser-id" ) ) );
        List<Object[]> adress = adressService.listAdressByIdUser( Integer.parseInt( request.getParameter( "selectedUser-id" ) ) );
        //cities
        try {
            request.setAttribute( "user", user );
            request.setAttribute( "roles", roleList );
            request.setAttribute( "cities", citiesList );
            request.setAttribute( "allTypeAdress", allTypeAdress );
            request.setAttribute( "adress", adress );
        } catch ( Exception e ) {
            logger.log( Level.ERROR, "User error" + e.getMessage() );
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {

            List<RolesEntity> roleList = roleService.showAllRoles();
            List<CitiesEntity> citiesList = citieService.showAllCities();
            //Define role
            logger.log( Level.INFO, "Role: " + request.getParameter( "RoleUpdate" ) );
            logger.log( Level.INFO, "LastName: " + request.getParameter( "lastNameUpdate" ) );
            logger.log( Level.INFO, "first: " + request.getParameter( "firstNameUpdate" ) );
            logger.log( Level.INFO, "id user: " + request.getParameter( "iduserUpdate" ) );
            int parameterrole = Integer.parseInt( request.getParameter( "RoleUpdate" ) );
            RolesEntity role = new RolesEntity();
            for ( RolesEntity roles : roleList ) {
                if ( parameterrole == roles.getIdRole() ) {
                    role.setIdRole( roles.getIdRole() );
                    role.setRole( roles.getRole() );
                }
            }

            UsersEntity user = new UsersEntity();
            user.setIdUser( Integer.parseInt( request.getParameter( "iduserUpdate" ) ) );
            if ( request.getParameter( "activeUpdate" ).equals( "on" ) ) {
                user.setActive( true );
            } else {
                user.setActive( false );
            }
            user.setLastName( request.getParameter( "lastNameUpdate" ) );
            user.setFirstName( request.getParameter( "firstNameUpdate" ) );
            user.setDayOfBirth( Date.valueOf( request.getParameter( "dayOfBirthUpdate" ) ) );
            user.setInscriptionDate( Date.valueOf( request.getParameter( "inscriptionDateUpdate" ) ) );
            user.setVat( request.getParameter( "vatUpdate" ) );
            user.setMail( request.getParameter( "emailUpdate" ) );
            user.setPassword( request.getParameter( "passwordUpdate" ) );
            user.setLogin( request.getParameter( "loginUpdate" ) );
            user.setRoles( role );
            //            user.setActive( true );

            //retrieve adress data
            AdressEntity adress = new AdressEntity();
            adress.setStreet( request.getParameter( "streetUpdate" ) );
            adress.setNumber( Integer.parseInt( request.getParameter( "numberUpdate" ) ) );
            if ( request.getParameter( "boxUpdate" ).equals( "" ) ) {
                adress.setBox( 0 );
            } else {
                adress.setBox( Integer.parseInt( request.getParameter( "boxUpdate" ) ) );
            }
            //initialize a city and recover the user's city
            CitiesEntity city = new CitiesEntity();
            int paramCity = Integer.parseInt( request.getParameter( "cityUpdate" ) );
            for ( CitiesEntity citiesEntity : citiesList ) {
                if ( paramCity == citiesEntity.getIdCity() ) {
                    city.setIdCity( citiesEntity.getIdCity() );
                    city.setCitie( city.getCitie() );
                }
            }
            adress.setCity( city );

            //initialize entity entity join
            AdressUsersEntity adressUsers = new AdressUsersEntity();
            adressUsers.setUsers( user );
            adressUsers.setAddress( adress );
            TypeAdress typeAdress = TypeAdress.valueOf( request.getParameter( "TypeadresseUpdate" ) );
            adressUsers.setTypeAdress( typeAdress );
            if (request.getParameter( "passwordUpdate" ).equals( request.getParameter( "rpPasswordUpdate" ) )){
                userService.updateUser( user, adress, adressUsers );
                //Send parameter to JSP
                List<Object[]> userList = userService.showAllUsers();
                request.setAttribute( "user", userList );
                this.getServletContext().getRequestDispatcher( VUE_LISTUSER ).forward( request, response );
            } else {
                logger.log( Level.ERROR, "the password does not match " );
                String errorPassword = "Les deux mot de passe ne corresponde pas !!!";
                UserService userService = new UserService();
                List<Object[]> users = userService
                        .selectUserById( user.getIdUser() );
                //cities
                try {
                    request.setAttribute( "errorPassword", errorPassword );
                    request.setAttribute( "user", users );
                    request.setAttribute( "roles", roleList );
                    request.setAttribute( "cities", citiesList );
                    request.setAttribute( "allTypeAdress", allTypeAdress );
                } catch ( Exception e ) {
                    logger.log( Level.ERROR, "User error" + e.getMessage() );
                }
                this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
            }
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Error Servlet Update User " + e.getMessage() );
        }

    }
}

