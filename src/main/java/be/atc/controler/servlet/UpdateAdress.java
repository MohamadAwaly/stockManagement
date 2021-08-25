package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.CitiesEntity;
import be.atc.entities.RolesEntity;
import be.atc.service.AdressService;
import be.atc.service.CitieService;
import be.atc.service.RoleService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateAdress", value = "/UpdateAdress")
public class UpdateAdress extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UpdateAdress.class);
    EntityManager em = EMF.getEM();
    AdressService adressService = new AdressService(em);
    TypeAdress[] allTypeAdress = TypeAdress.values();
    private CitieService citieService = new CitieService();
    public static final String VUE = "/views/UpdateAdress.jsp";
    public static final String VUE_UPDATEUSER = "/views/updateUser.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> adress = adressService
                .adressbyIdAdress(Integer.parseInt(request.getParameter("selected-IdAdress")));
        List<CitiesEntity> citiesList = citieService.showAllCities();
        int idUser = 0;
        try {
            idUser = Integer.parseInt(request.getParameter("user-id"));
            logger.log(Level.INFO,"iduser dans le get: " + idUser);
        } catch (NumberFormatException e){
            logger.log(Level.ERROR,"error - impossible to get id user");
        }
        try {
            request.setAttribute("iduser", idUser);
            request.setAttribute("adress", adress);
            request.setAttribute("cities", citiesList);
            request.setAttribute("allTypeAdress", allTypeAdress);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error - update adress");
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdressEntity adress = new AdressEntity();
        try {
            adress.setIdAdress(Integer.parseInt(request.getParameter("idadressUpdate")));
        } catch (NumberFormatException e){
            /*error*/
        }
        adress.setStreet(request.getParameter("street"));
        try {
            adress.setNumber(Integer.parseInt(request.getParameter("number")));
        } catch (NumberFormatException e){
            /*error*/
        }
        if(!request.getParameter("box").isEmpty()) {
            try {
                adress.setBox(Integer.parseInt(request.getParameter("box")) );
            } catch (NumberFormatException e){
                logger.log(Level.ERROR," Box is empty ");
                /*ignored*/
            }
        }
        List<CitiesEntity> citiesList = citieService.showAllCities();
        CitiesEntity city = new CitiesEntity();
        int paramCity = Integer.parseInt(request.getParameter("city"));
        for (CitiesEntity citiesEntity : citiesList) {
            if (paramCity == citiesEntity.getIdCity()) {
                city.setIdCity(citiesEntity.getIdCity());
                city.setCitie(city.getCitie());
            }
        }
        adress.setCity(city);
        AdressUsersEntity adressUsers = new AdressUsersEntity();
        adressUsers.setAddress(adress);
        TypeAdress typeAdress = TypeAdress.valueOf( request.getParameter( "typeAdresse" ) );
        adressUsers.setTypeAdress(typeAdress);
        AdressService adressService = new AdressService(em);
        adressService.updateadress(adress,adressUsers);
        logger.log(Level.INFO,"adress updated");

        //get id user to send user and list adress to jsp
        int iduser = Integer.parseInt(request.getParameter("idUseradressUpdate"));
        UserService userService = new UserService(em);
        List<Object[]> user = userService
                .selectUserById( iduser);
        List<Object[]> adressList = adressService
                .listAdressByIdUser( iduser );
        RoleService roleService  = new RoleService();
        List<RolesEntity>  roleList      = roleService.showAllRoles();

        try {
            request.setAttribute( "user", user );
            request.setAttribute( "adress", adressList );
            request.setAttribute( "roles", roleList );

        } catch (Exception e){
            /*ignored*/
        } finally {
            em.close();
        }
        this.getServletContext().getRequestDispatcher(VUE_UPDATEUSER).forward(request, response);


    }
}
