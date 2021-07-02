package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.*;
import be.atc.service.AdressService;
import be.atc.service.CitieService;
import be.atc.service.RoleService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.security.auth.login.Configuration;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet( name = "AddUser", value = "/adduser" )
public class AddUser extends HttpServlet {
    private static      Logger            logger               = Logger.getLogger( AddUser.class );
    public static final String            VUE                  = "/views/addUser.jsp";
    public static final String            VUE_LISTEUTILISATEUR = "/views/showUsers.jsp";
    private             UserService       userService          = new UserService();
    private             RoleService       roleService          = new RoleService();
    private             CitieService      citieService         = new CitieService();
    private             AdressService     adressService        = new AdressService();
    private             AdressEntity      adress               = new AdressEntity();
    private             AdressUsersEntity adressUser           = new AdressUsersEntity();
    private             UserService       user                 = new UserService();

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        List<RolesEntity> roleList = roleService.showAllRoles();
        List<CitiesEntity> citiesList = citieService.showAllCities();

        TypeAdress[] allTypeAdress = TypeAdress.values();
        //        for ( TypeAdress adress : allTypeAdress ) {
        //
        //            logger.log( Level.INFO, "enum: " + adress );
        //        }

        try {
            request.setAttribute( "roles", roleList );
            request.setAttribute( "cities", citiesList );
            request.setAttribute( "allTypeAdress", allTypeAdress );
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Erreur d'envoie a la jsp: " + e.getMessage() );
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        //        EntityManager em = EMF.getEM();
        //        EntityTransaction trans = em.getTransaction();

        try {
            List<RolesEntity> roleList = roleService.showAllRoles();
            List<CitiesEntity> citiesList = citieService.showAllCities();
            //Define role
            int parameterrole = Integer.parseInt( request.getParameter( "role" ) );
            RolesEntity role = new RolesEntity();
            for ( RolesEntity roles : roleList ) {
                if ( parameterrole == roles.getIdRole() ) {
                    role.setIdRole( roles.getIdRole() );
                    role.setRole( role.getRole() );
                }
            }

            //encrypt password
            String password = request.getParameter( "password" );
            String passwordHached = BCrypt.hashpw( password, BCrypt.gensalt() );

            //today's date
            LocalDate currentDate = LocalDate.now();

            //retrieve user data
            UsersEntity newuser = new UsersEntity();
            newuser.setLastName( request.getParameter( "lastName" ) );
            newuser.setFirstName( request.getParameter( "firstName" ) );
            newuser.setDayOfBirth( Date.valueOf( request.getParameter( "dayOfBirth" ) ) );
            newuser.setInscriptionDate( Date.valueOf( currentDate ) );
            newuser.setVat( request.getParameter( "vat" ) );
            newuser.setMail( request.getParameter( "email" ) );
            newuser.setPassword( passwordHached );
            newuser.setLogin( request.getParameter( "login" ) );
            newuser.setRoles( role );
            newuser.setActive( true );

            //retrieve adress data
            AdressEntity adress = new AdressEntity();
            adress.setStreet( request.getParameter( "street" ) );
            adress.setNumber( Integer.parseInt( request.getParameter( "number" ) ) );
            adress.setBox( Integer.parseInt( request.getParameter( "box" ) ) );
            //initialize a city and recover the user's city
            CitiesEntity city = new CitiesEntity();
            int paramCity = Integer.parseInt( request.getParameter( "city" ) );
            for ( CitiesEntity citiesEntity : citiesList ) {
                if ( paramCity == citiesEntity.getIdCity() ) {
                    city.setIdCity( citiesEntity.getIdCity() );
                    city.setCitie( city.getCitie() );
                }
            }
            adress.setCity( city );

            //initialize entity entity join
            AdressUsersEntity adressUsers = new AdressUsersEntity();
            adressUsers.setUsers( newuser );
            adressUsers.setAddress( adress );

            TypeAdress typeAdress = TypeAdress.valueOf( request.getParameter( "typeAdresse" ) );
            adressUsers.setTypeAdress( typeAdress );

            userService.addUser( newuser, adress, adressUsers );



//            adressService.addUserAdress( adress );

            //Send parameter to JSP
            List<UsersEntity> userList = user.showAllUsers();
            request.setAttribute( "user", userList );
            this.getServletContext().getRequestDispatcher( VUE_LISTEUTILISATEUR ).forward( request, response );
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Erreur Servlet " + e.getMessage() );
        }

    }
}
