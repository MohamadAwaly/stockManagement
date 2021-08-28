package be.atc.controler.servlet;

import be.atc.controler.pdf.PdfManager;
import be.atc.service.SupplierService;
import com.itextpdf.text.DocumentException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetPdfCmdSupp", value = "/GetPdfCmdSupp")
public class GetPdfCmdSupp extends HttpServlet {
    private SupplierService supplierService = new SupplierService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Object[]> lst_supplierCommand = supplierService.CommandSupplierList(request.getParameter("searchBar"));
        try {
            PdfManager.createPdf("C:\\Users\\Jiwai\\Downloads\\stockManagementPDF.pdf",lst_supplierCommand);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
