package be.atc.controler.servlet;

import be.atc.service.SupplierService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommandSupplierCreate", value = "/CommandSupplierCreate")
public class CommandSupplierCreate extends HttpServlet {
    public static final String VUE = "/views/CommandSupplierCreate.jsp";
    private SupplierService supplierService = new SupplierService();
    private Logger logger = Logger.getLogger(SuppliersShowAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
