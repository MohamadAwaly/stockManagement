package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserProfile", value = "/UserProfile")
public class UserProfile extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserProfile.class);
    public static final  String      VUE    = "/views/UserProfile.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        String userlogin = (String) session.getAttribute("SessionUser");
        List<UsersEntity> user = userService.profile(userlogin);
        try {
            request.setAttribute( "user", user );
        }catch (Exception e){
            /*error*/
        }
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
