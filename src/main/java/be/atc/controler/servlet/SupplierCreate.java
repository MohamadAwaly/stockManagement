package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.entities.SuppliersEntity;
import be.atc.service.SupplierService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "supplierCreate", value = "/supplierCreate")
public class SupplierCreate extends HttpServlet {

    public static final String VUE = "/views/supplierCreate.jsp";
    public static final String VUE_SupplierList = "/views/suppliersShowAll.jsp";
    private             SupplierService supplierService = new SupplierService();
    private             Logger logger = Logger.getLogger(SupplierCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierName = request.getParameter("supplierName").toString();
        logger.log(Level.INFO,"RECU : "+supplierName);
        SuppliersEntity newSuppliersEntity = new SuppliersEntity();
        newSuppliersEntity.setName(supplierName);
        logger.log(Level.INFO,"Entity : " + newSuppliersEntity);
        this.getServletContext().getRequestDispatcher(VUE_SupplierList).forward(request,response);
        //supplierService.supplierCreate(newSuppliersEntity);
    }
}
