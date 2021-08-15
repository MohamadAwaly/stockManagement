package be.atc.controler.servlet;

import be.atc.service.SupplierService;
import com.mysql.cj.xdevapi.JsonParser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SupplierExist", value = "/SupplierExist")
public class SupplierExist extends HttpServlet {
    private SupplierService supplierService = new SupplierService();
    private Logger logger = Logger.getLogger(SupplierExist.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object mySupplier = supplierService.supplierExist(request.getParameter("name"));
        logger.log(Level.INFO," ==== Servlet ==== : " + mySupplier);
        //String myString = "Voici un String";
        response.getWriter().write(mySupplier.toString());
        //request.setAttribute("supplier","supplierService");
    }
}
