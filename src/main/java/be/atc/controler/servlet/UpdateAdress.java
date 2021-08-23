package be.atc.controler.servlet;

import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.CitiesEntity;
import be.atc.service.AdressService;
import be.atc.service.CitieService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateAdress", value = "/UpdateAdress")
public class UpdateAdress extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UpdateAdress.class);
    AdressService adressService = new AdressService();
    TypeAdress[] allTypeAdress = TypeAdress.values();
    private CitieService citieService = new CitieService();
    public static final String VUE = "/views/UpdateAdress.jsp";
    public static final String VUE_UPDATEUSER = "/views/updateUser.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> adress = adressService
                .adressbyIdAdress(Integer.parseInt(request.getParameter("selected-IdAdress")));
        List<CitiesEntity> citiesList = citieService.showAllCities();

//        int idUser = Integer.parseInt(request.getParameter("selectedUserUpdate-id"));
//        logger.log(Level.INFO,"iduser: " + idUser);
        try {
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
//        adress.setCity(request.getParameter("city"));

        AdressUsersEntity adressUsers = new AdressUsersEntity();
        adressUsers.setAddress(adress);
        TypeAdress typeAdress = TypeAdress.valueOf( request.getParameter( "typeAdresse" ) );
        adressUsers.setTypeAdress(typeAdress);

        AdressService adressService = new AdressService();
        adressService.updateadress(adress,adressUsers);

        this.getServletContext().getRequestDispatcher(VUE_UPDATEUSER).forward(request, response);


    }
}
