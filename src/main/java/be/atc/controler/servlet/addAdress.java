package be.atc.controler.servlet;

import be.atc.controler.enumm.TypeAdress;
import be.atc.entities.AdressEntity;
import be.atc.entities.AdressUsersEntity;
import be.atc.entities.CitiesEntity;
import be.atc.entities.UsersEntity;
import be.atc.service.CitieService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "addAdress", value = "/addAdress")
public class addAdress extends HttpServlet {
    public static final String VUE = "/views/addAdress.jsp";
    public static final String VUE_UPDATEUSER = "/views/updateUser.jsp";
    private CitieService citieService = new CitieService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_UPDATEUSER).forward(request, response);


    }
}
