package be.atc.controler.servlet;

import be.atc.service.SupplierService;
import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommandSuppliersSearch", value = "/CommandSuppliersSearch")
public class CommandSuppliersSearch extends HttpServlet {
    private SupplierService supplierService = new SupplierService();
    private Logger logger = Logger.getLogger(CommandSuppliersSearch.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> lst_CommandSupplierBatch = supplierService.CommandSupplierList(request.getParameter("searchBar"));
        logger.log(Level.INFO,lst_CommandSupplierBatch);
        String json = new Gson().toJson(lst_CommandSupplierBatch);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
