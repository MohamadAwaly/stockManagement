package be.atc.controler.servlet;

import be.atc.entities.RolesEntity;
import be.atc.entities.UsersEntity;
import be.atc.service.RoleService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import javax.security.auth.login.Configuration;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "AddUser", value = "/adduser")
public class AddUser extends HttpServlet {
    public static final String VUE = "/views/addUser.jsp";
    public UserService userService = new UserService();
    private static Logger logger = Logger.getLogger(AddUser.class);
    private RoleService roleService = new RoleService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RolesEntity> roleList = roleService.showAllRoles();
        try {
            request.setAttribute("roles", roleList);
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur d'enoie de list des role a la jsp: " + e.getMessage());
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<RolesEntity> roleList = roleService.showAllRoles();
            //Define role
            int parameterrole = Integer.parseInt(request.getParameter("role"));
            RolesEntity role = new RolesEntity();
            for (RolesEntity roles: roleList) {
                if (parameterrole == roles.getIdRole()){
                    role.setIdRole(roles.getIdRole());
                    role.setRole(role.getRole());
                }
            }

            //encrypt password
            String password = request.getParameter("password");
            String passwordHached = BCrypt.hashpw(password, BCrypt.gensalt());

            //today's date
            LocalDate currentDate = LocalDate.now();

            //initialize new user & add user
            UsersEntity newuser = new UsersEntity();
            newuser.setLastName(request.getParameter("lastName"));
            newuser.setFirstName(request.getParameter("firstName"));
            newuser.setDayOfBirth(Date.valueOf(request.getParameter("dayOfBirth")));
            newuser.setInscriptionDate(Date.valueOf(currentDate));
            newuser.setVat(request.getParameter("vat"));
            newuser.setMail(request.getParameter("email"));
            newuser.setPassword(passwordHached);
            newuser.setLogin(request.getParameter("login"));
            newuser.setRoles(role);
            newuser.setActive(true);
            userService.addUser(newuser);

            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } catch (Exception e) {
            logger.log(Level.INFO, "Erreur " + e.getMessage());
        }

    }
}
