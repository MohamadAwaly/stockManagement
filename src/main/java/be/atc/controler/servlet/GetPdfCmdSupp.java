package be.atc.controler.servlet;

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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


@WebServlet(name = "GetPdfCmdSupp", value = "/GetPdfCmdSupp")
public class GetPdfCmdSupp extends HttpServlet {
    private SupplierService supplierService = new SupplierService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Object[]> lst_supplierCommand = supplierService.CommandSupplierList(request.getParameter("searchBar"));
        // Date Format Adapt for commande
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        for (Object[] cmdSuppBatch:lst_supplierCommand
        ) {
            cmdSuppBatch[1]  = dateFormat.format(cmdSuppBatch[1]);
        }
        try {
            Path path = Paths.get("C:\\StockManagement\\");
            if (!Files.exists(path)){
                Files.createDirectories(path);
                PdfManager.createPdfCommandsSuppliers("C:\\stockmanagement\\stockManagementPDF.pdf",lst_supplierCommand);
            }else{
            PdfManager.createPdfCommandsSuppliers("C:\\stockmanagement\\stockManagementPDF.pdf",lst_supplierCommand);
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
