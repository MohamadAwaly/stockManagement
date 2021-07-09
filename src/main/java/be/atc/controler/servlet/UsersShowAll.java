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
    private             UserService user = new UserService();
    public static final String      VUE  = "/views/showUsers.jsp";
    private static final Logger logger = Logger.getLogger(UsersShowAll.class);

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
//        List<UsersEntity> userList = user.showAllUsers();
        List<Object[]> userTest = user.showAllUserstest();
        List<UsersEntity> testListeUser = new ArrayList<>();
        List<AdressEntity> testListeAdress = new ArrayList<>();
        UsersEntity testUSer = new UsersEntity();
        AdressEntity adress = new AdressEntity();
        for (Object[] test : userTest){
            testListeUser.add((UsersEntity) test[0]);
            testListeAdress.add((AdressEntity) test[1]);
            testUSer = (UsersEntity) test[0];
            adress = (AdressEntity) test[1];
        }
//        logger.log(Level.INFO, "testUser: " + testUSer.getIdUser()+" "+testUSer.getLastName() + " " + adress.getIdAdress()+ " "+adress.getStreet() );
        try {
            request.setAttribute( "user", userTest);
//            request.setAttribute( "adress", testListeAdress);
        } catch ( Exception e ) {
            System.out.println( "Erreur servlet get" );
        }

//        try {
//            request.setAttribute( "user", userList );
//        } catch ( Exception e ) {
//            System.out.println( "Erreur servlet get" );
//        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    }
}
