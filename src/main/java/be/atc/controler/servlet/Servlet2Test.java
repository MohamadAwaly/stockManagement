package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.controler.dao.EntityTest;
import be.atc.entities.UsersEnt;
import be.atc.service.UserService;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet2Test", value = "/Servlet2Test")
public class Servlet2Test extends HttpServlet {
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger
            .getLogger(Servlet2Test.class);
    private EntityTest pers = new EntityTest();
    private UserService user = new UserService();
    public static final String VUE = "/views/showUsers.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            request.setAttribute("adresse", pers.allUser());
        } catch (Exception e) {
            System.out.println("Erreur servlet");
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = EMF.getEM();
//        Query query = em.createNamedQuery("User.findall");
        List<UsersEnt> userList = user.showAllUser();
//        userList = user.showAllUser();
        try {
            request.setAttribute("adresse", userList);
        } catch (Exception e) {
            System.out.println("Erreur servlet get");
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
