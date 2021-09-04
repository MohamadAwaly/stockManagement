package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommandSupplierShowAll", value = "/CommandSupplierShowAll")
public class CommandSupplierShowAll extends HttpServlet {
    public static final String        VUE = "/views/CommandSupplierList.jsp";
    private EntityManager               em = EMF.getEM();
    private Logger                  logger = Logger.getLogger(CommandSupplierShowAll.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "doGET sevlet entered");
        Query query =  em.createNamedQuery("commandSuppliers.FindAll");
        List<Object[]> commandSupplier = query.getResultList();
        request.setAttribute("commandSuppliers",commandSupplier);
        request.getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
