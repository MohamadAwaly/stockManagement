package be.atc.controler.servlet;

import be.atc.service.ProductService;
import be.atc.service.SupplierService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommandSupplierCreate", value = "/CommandSupplierCreate")
public class CommandSupplierCreate extends HttpServlet {
    public static final String VUE = "/views/CommandSupplierCreate.jsp";
    private SupplierService supplierService = new SupplierService();
    private ProductService productService = new ProductService();
    private Logger logger = Logger.getLogger(SuppliersShowAll.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> lst_Product = productService.ShowIdAndNameProduct();
        List<Object[]> lst_Supplier = supplierService.suppliersShowAll();
        request.setAttribute("lst_product",lst_Product);
        request.setAttribute("suppliers",lst_Supplier);
        request.getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
