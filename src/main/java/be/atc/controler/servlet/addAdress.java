package be.atc.controler.servlet;

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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addAdress", value = "/addAdress")
public class addAdress extends HttpServlet {
    public static final String VUE = "/views/addAdress.jsp";
    public static final String VUE_UPDATEUSER = "/views/updateUser.jsp";
    private CitieService citieService = new CitieService();
    private static final Logger logger = Logger.getLogger(addAdress.class);
    TypeAdress[] allTypeAdress = TypeAdress.values();
    List<CitiesEntity> citiesList = citieService.showAllCities();
    private RoleService roleService  = new RoleService();
    List<RolesEntity>  roleList      = roleService.showAllRoles();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get id user
        int idUser = Integer.parseInt(request.getParameter("selectedUserUpdate-id"));
        //send request to jsp
        try {
            request.setAttribute("id", idUser);
            request.setAttribute("allTypeAdress", allTypeAdress);
            request.setAttribute("cities", citiesList);
        } catch (Exception e) {
            logger.log(Level.ERROR, "error - send request to jsp: " + e.getMessage());
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get id user
        int idUser = Integer.parseInt(request.getParameter("iduserUpdate"));

        //get the new adress
        AdressEntity adress = new AdressEntity();
        adress.setStreet(request.getParameter("street"));
        adress.setNumber(Integer.parseInt(request.getParameter("number")));
        adress.setBox(Integer.parseInt(request.getParameter("box")));

        //get type of adress
        TypeAdress typeAdress = TypeAdress.valueOf(request.getParameter("typeAdresse"));

        AdressUsersEntity adressUsers = new AdressUsersEntity();
        adressUsers.setTypeAdress(typeAdress);

        // get city
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
        String error = "";
        AdressService adressService = new AdressService();
        boolean ifTypeAdressExist = adressService.verfyIfTypeAdressExist(typeAdress, idUser);
        logger.log(Level.INFO,"type of adress: (return methode) " + ifTypeAdressExist);
        if(ifTypeAdressExist){
            adressService.addMultipleAdress(idUser, adress, adressUsers);
            logger.log(Level.INFO,"Adress added");
        } else {
            error = "l'utilisateur possede deja une adresse de " + typeAdress;
        }


        //request JSP
        UserService userService = new UserService();
        List<Object[]> user = userService
                .selectUserById( Integer.parseInt( request.getParameter( "iduserUpdate" ) ) );
        List<Object[]> adressList = adressService
                .listAdressByIdUser( Integer.parseInt( request.getParameter( "iduserUpdate" ) ) );

        //iduserUpdate
        int id = Integer.parseInt(request.getParameter("iduserUpdate"));

        try {
            if (error.equals("")){
                request.setAttribute( "user", user );
                request.setAttribute( "roles", roleList );
                request.setAttribute( "cities", citiesList );
                request.setAttribute( "allTypeAdress", allTypeAdress );
                request.setAttribute( "adress", adressList );
            } else {
                request.setAttribute("error", error);
                request.setAttribute("id", id);
                request.setAttribute("allTypeAdress", allTypeAdress);
                request.setAttribute("cities", citiesList);
            }
        } catch ( Exception e ) {
            logger.log( Level.ERROR, "User error" + e.getMessage() );
        }

        if (error.equals("")){
            this.getServletContext().getRequestDispatcher(VUE_UPDATEUSER).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }



    }


}
