package be.atc.controler.servlet;

import be.atc.service.SupplierService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "suppliersShowAll", value = "/suppliersShowAll")
public class SuppliersShowAll extends HttpServlet {
    public static final String VUE = "/views/suppliersShowAll.jsp";
    private             SupplierService supplierService = new SupplierService();
    private             Logger logger = Logger.getLogger(SuppliersShowAll.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Object[]> lst_suppliers = supplierService.suppliersShowAll();
        logger.log(Level.INFO,"___INFO SUPP____ "+lst_suppliers);
        request.setAttribute("suppliers",lst_suppliers);
        request.setAttribute("nom","Laurent de Jambes");
        request.getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
