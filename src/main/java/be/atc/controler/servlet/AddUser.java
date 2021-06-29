package be.atc.controler.servlet;

import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "AddUser", value = "/adduser")
public class AddUser extends HttpServlet {
    public static final String VUE = "/views/addUser.jsp";
    public UserService userService = new UserService();
    private static Logger logger = Logger.getLogger(AddUser.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.log(Level.INFO, "Servlet add user");

            LocalDate currentDate = LocalDate.now();
            logger.log(Level.INFO, "Date dans la servlet " + currentDate);
            UsersEntity newuser = new UsersEntity();
            newuser.setLastName(request.getParameter("lastName"));
            newuser.setFirstName(request.getParameter("firstName"));
            newuser.setDayOfBirth(Date.valueOf(request.getParameter("dayOfBirth")));
            newuser.setInscriptionDate(Date.valueOf(currentDate));
            newuser.setVat(request.getParameter("vat"));
            newuser.setMail(request.getParameter("email"));
            newuser.setPassword(request.getParameter("password"));
            newuser.setLogin(request.getParameter("login"));
            newuser.setActive(true);
            userService.addUser(newuser);
            logger.log(Level.INFO, "apres le add utilisateur");
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } catch (Exception e) {
            logger.log(Level.INFO,"Erreur " +  e.getMessage());
        }

    }
}
