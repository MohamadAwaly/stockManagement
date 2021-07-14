package be.atc.controler.servlet;

import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet( name = "UserUpdate", value = "/UserUpdate" )
public class UserUpdate extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        UserService userService = new UserService();
        List<Object[]> usersList = userService.selectUserById( Integer.parseInt( request.getParameter( "selectedUserLogin" ) ) );

        for ( List <Object[]> list: usersList
               ) {

        }

        UsersEntity user = new UsersEntity();
        AdressEntity adress = new AdressEntity();
        AdressUsersEntity adressUser = new AdressUsersEntity();

        user.setIdUser( Integer.parseInt( request.getParameter( "selectedUserLogin" ) )  );



    }
}
