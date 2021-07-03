package be.atc.controler.servlet;

import be.atc.entities.AdressUsersEntity;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;

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

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        List<UsersEntity> userList = user.showAllUsers();
        try {
            request.setAttribute( "user", userList );
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
