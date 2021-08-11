package be.atc.controler.servlet;

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
    private CitieService citieService = new CitieService();
    public static final String VUE = "/views/UpdateAdress.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> adress = adressService
                .adressbyIdAdress(Integer.parseInt(request.getParameter("selected-IdAdress")));
        List<CitiesEntity> citiesList = citieService.showAllCities();
        try {
            request.setAttribute("adress", adress);
            request.setAttribute("cities", citiesList);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error - update adress");
        }
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }
}
