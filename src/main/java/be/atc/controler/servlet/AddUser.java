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
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet( name = "AddUser", value = "/adduser" )
public class AddUser extends HttpServlet {
    private static final Logger       logger       = Logger.getLogger( AddUser.class );
    public static final  String       VUE          = "/views/addUser.jsp";
    public static final  String       VUE_LISTUSER = "/views/showUsers.jsp";
    public static final  String       VUE_ADDUSER  = "/views/addUser.jsp";
    EntityManager em = EMF.getEM(); ;
    private              UserService  userService  = new UserService(em);
    private              RoleService  roleService  = new RoleService();
    private              CitieService citieService = new CitieService();
    private              UserService  user         = new UserService(em);
    List<RolesEntity>  roleList      = roleService.showAllRoles();
    List<CitiesEntity> citiesList    = citieService.showAllCities();
    TypeAdress[]       allTypeAdress = TypeAdress.values();

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

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
        try {
            List<RolesEntity> roleList = roleService.showAllRoles();
            List<CitiesEntity> citiesList = citieService.showAllCities();
            //Define role
            int parameterrole = Integer.parseInt( request.getParameter( "role" ) );
            RolesEntity role = new RolesEntity();
            for ( RolesEntity roles : roleList ) {
                if ( parameterrole == roles.getIdRole() ) {
                    role.setIdRole( roles.getIdRole() );
                    role.setRole( roles.getRole() );
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
            if (!request.getParameter( "vat" ).isEmpty() ){
                newuser.setVat( request.getParameter( "vat" ) );
            } else {
                newuser.setVat(null);
            }
            if ( !request.getParameter( "email" ).isEmpty() ){
                newuser.setMail( request.getParameter( "email" ) );
            }
            newuser.setPassword( passwordHached );
            newuser.setLogin( request.getParameter( "login" ) );
            newuser.setRoles( role );
            newuser.setActive( true );

            //retrieve adress data
            AdressEntity adress = new AdressEntity();
            adress.setStreet( request.getParameter( "street" ) );
            adress.setNumber( Integer.parseInt( request.getParameter( "number" ) ) );

            try {
                adress.setBox( Integer.parseInt( request.getParameter( "box" ) ) );
            } catch (Exception e ){
                /*ignored*/
            }
            //initialize a city and get the user's city
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

            boolean adduser = false;
            adduser = userService.addUser( newuser, adress, adressUsers );
            //            HttpSession session = request.getSession();
            String errorUserExist = "l'utilisateur " + newuser.getLogin() + " ou le numéro de tva " + newuser.getVat()+ " existe déja ";
            //Send parameter to JSP
            if ( adduser ) {
                List<Object[]> userList = user.showAllUsers();
                request.setAttribute( "user", userList );
                this.getServletContext().getRequestDispatcher( VUE_LISTUSER ).forward( request, response );
            } else {
                request.setAttribute( "roles", roleList );
                request.setAttribute( "cities", citiesList );
                request.setAttribute( "allTypeAdress", allTypeAdress );
                request.setAttribute( "error", errorUserExist );
                request.setAttribute( "user", newuser );
                this.getServletContext().getRequestDispatcher( VUE_ADDUSER ).forward( request, response );
            }
        } catch ( Exception e ) {
            logger.log( Level.INFO, "Erreur Servlet " + e.getMessage() );
        }

    }
}
