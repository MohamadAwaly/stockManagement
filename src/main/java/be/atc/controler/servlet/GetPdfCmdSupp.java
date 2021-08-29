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
import java.util.ArrayList;
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


        List<String> lst_to = new ArrayList<String>();
        lst_to.add("jeanyves.laurent@promsocatc.net");
        lst_to.add("awalymhassan@hotmail.com");

        Mail mail = new Mail();
        mail.setListTo(lst_to);
        mail.setSubject("Test Stock management");
        mail.setMsgBody("TEST 1234");
        mail.setNick(" je suis Nick !");
        mail.setFrom("stockmanagementatc@gmail.com");
        MailSender.sendMail(mail);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
