package be.atc.controler.servlet;

import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.*;
import be.atc.service.AdressService;
import be.atc.service.CitieService;
import be.atc.service.RoleService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "userUpdate", value = "/userUpdate")
public class UserUpdate extends HttpServlet {
    public static final String VUE = "/views/updateUser.jsp";
    public static final String VUE_LISTUSER = "/views/showUsers.jsp";
    public static final String VUE_PROFILE = "/views/UserProfile.jsp";
    public static final  String       VUE_HOME  = "/index.jsp";


    private static final Logger logger = Logger.getLogger(UserUpdate.class);
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    private CitieService citieService = new CitieService();

    List<RolesEntity> roleList = roleService.showAllRoles();
    List<CitiesEntity> citiesList = citieService.showAllCities();
    TypeAdress[] allTypeAdress = TypeAdress.values();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        AdressService adressService = new AdressService();
        List<Object[]> user = userService
                .selectUserById(Integer.parseInt(request.getParameter("selectedUser-id")));
        logger.log(Level.INFO, "ID : " + Integer.parseInt(request.getParameter("selectedUser-id")));
        List<Object[]> adress = adressService
                .listAdressByIdUser(Integer.parseInt(request.getParameter("selectedUser-id")));

        try {
            request.setAttribute("user", user);
            request.setAttribute("roles", roleList);
            request.setAttribute("cities", citiesList);
            request.setAttribute("allTypeAdress", allTypeAdress);
            request.setAttribute("adress", adress);
        } catch (Exception e) {
            logger.log(Level.ERROR, "User error" + e.getMessage());
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<RolesEntity> roleList = roleService.showAllRoles();
            List<CitiesEntity> citiesList = citieService.showAllCities();
            //Define role
            int parameterrole = Integer.parseInt(request.getParameter("RoleUpdate"));
            RolesEntity role = new RolesEntity();
            for (RolesEntity roles : roleList) {
                if (parameterrole == roles.getIdRole()) {
                    role.setIdRole(roles.getIdRole());
                    role.setRole(roles.getRole());
                }
            }
            UsersEntity user = new UsersEntity();
            try {
                user.setIdUser(Integer.parseInt(request.getParameter("iduserUpdate")));
            } catch ( Exception e ){
                logger.log( Level.INFO,"Error - id user" );
            }
            logger.log( Level.INFO, "id: " + Integer.parseInt(request.getParameter("iduserUpdate")) );
            String isActive = request.getParameter("activeUpdate");
            if (isActive != null && request.getParameter("activeUpdate").equals("on")) {
                user.setActive(true);
            } else {
                user.setActive(false);
            }
            user.setLastName(request.getParameter("lastNameUpdate"));
            user.setFirstName(request.getParameter("firstNameUpdate"));
            logger.log( Level.INFO,"day of birth : " +request.getParameter("dayOfBirthUpdate")  );
            if (!request.getParameter("dayOfBirthUpdate").isEmpty()){
                user.setDayOfBirth(Date.valueOf(request.getParameter("dayOfBirthUpdate")));
            }
            user.setInscriptionDate(Date.valueOf(request.getParameter("inscriptionDateUpdate")));
            if (!request.getParameter("vatUpdate").isEmpty()){
                user.setVat(request.getParameter("vatUpdate"));
            }
            if (!request.getParameter("emailUpdate").isEmpty()){
                user.setMail(request.getParameter("emailUpdate"));
            }
            logger.log(Level.INFO, "password updated: " + request.getParameter("passwordUpdate"));
            user.setPassword(request.getParameter("passwordUpdate"));
            user.setLogin(request.getParameter("loginUpdate"));
            user.setRoles(role);

            //retrieve adress data
            AdressEntity adress = new AdressEntity();

            //initialize entity entity join
            AdressUsersEntity adressUsers = new AdressUsersEntity();

            if (request.getParameter("passwordUpdate").equals(request.getParameter("rpPasswordUpdate"))) {
                userService.updateUser(user, adress, adressUsers);
                //Send parameter to JSP
                List<Object[]> userList = userService.showAllUsers();
                //retrieve session
                HttpSession session = request.getSession();
                UsersEntity userSession = (UsersEntity) session.getAttribute("SessionUserEntity");
                request.setAttribute("user", userList);

                if (userSession.getRoles().getRole().trim().equals("administrateur")) {
                    logger.log(Level.INFO, "session administrateur");
                } else {
                    logger.log(Level.INFO, "session customer or other");
                }
                if (userSession.getRoles().getRole().trim().equals("administrateur")) {
                    this.getServletContext().getRequestDispatcher(VUE_LISTUSER).forward(request, response);
                } else {
                    this.getServletContext().getRequestDispatcher(VUE_HOME).forward(request, response);
                }
            } else {
                logger.log(Level.ERROR, "the password does not match ");
                String errorPassword = "Les deux mot de passe ne corresponde pas !!!";
                UserService userService = new UserService();
                List<Object[]> users = userService
                        .selectUserById(user.getIdUser());
                //cities

                try {
                    request.setAttribute("errorPassword", errorPassword);
                    request.setAttribute("user", users);
                    request.setAttribute("roles", roleList);
                    request.setAttribute("cities", citiesList);
                    request.setAttribute("allTypeAdress", allTypeAdress);
                } catch (Exception e) {
                    logger.log(Level.ERROR, "User error" + e.getMessage());
                }
                    this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.INFO, "Error Servlet Update User: " + e.getMessage());
        }

    }
}

