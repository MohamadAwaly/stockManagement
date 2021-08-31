package be.atc.controler.servlet;

import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersSearchAjax", value = "/UsersSearchAjax")
public class UsersSearchAjax extends HttpServlet {

    private static final Logger logger = Logger.getLogger( UsersSearchAjax.class );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService user   = new UserService();
        List<Object[]> users = user.searchUser(request.getParameter("searchUserBar"));
//        List<Object[]> users = user.showAllUsers();
        logger.log(Level.INFO,"users ajax: " + users);
        String json = new Gson().toJson(users);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
