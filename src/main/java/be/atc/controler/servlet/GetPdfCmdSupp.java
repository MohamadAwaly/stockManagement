package be.atc.controler.servlet;

import be.atc.controler.mail.Mail;
import be.atc.controler.mail.MailSender;
import be.atc.controler.pdf.PdfManager;
import be.atc.service.SupplierService;
import com.itextpdf.text.DocumentException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



@WebServlet(name = "GetPdfCmdSupp", value = "/GetPdfCmdSupp")
public class GetPdfCmdSupp extends HttpServlet {
    private SupplierService supplierService = new SupplierService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Object[]> lst_supplierCommand = supplierService.CommandSupplierList(request.getParameter("searchBar"));
        try {
            Path path = Paths.get("C:\\StockManagement\\");
            if (!Files.exists(path)){
                Files.createDirectories(path);
                PdfManager.createPdf("C:\\stockmanagement\\stockManagementPDF.pdf",lst_supplierCommand);
            }else{
            PdfManager.createPdf("C:\\stockmanagement\\stockManagementPDF.pdf",lst_supplierCommand);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
