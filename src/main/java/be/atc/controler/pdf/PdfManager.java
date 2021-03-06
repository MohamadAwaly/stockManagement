package be.atc.controler.pdf;

import com.sun.mail.util.MailLogger;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PdfManager {
    public static void createPdfCommandsSuppliers(String destination, List<Object[]> lst_objects)
            throws FileNotFoundException, DocumentException, DocumentException {
        Document document = new Document();
        FileOutputStream file = new FileOutputStream(destination);
        PdfWriter.getInstance(document,file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

        document.open();
        // Creation de 2 paragraphes et mise en page
        Paragraph title = new Paragraph("Liste des commandes fournisseurs ");
        Paragraph titleDate = new Paragraph("Date : "+ dateFormat.format(new Date()));
        title.setSpacingAfter(20);
        titleDate.setSpacingAfter(10);

        // Creation d'une table
        int numberOfColumns = 4;
        PdfPTable table = new PdfPTable(numberOfColumns); // Tout les colonnes doivent être remplis
        //En-tête
        table.addCell("# Commande");
        table.addCell("Fournisseur");
        table.addCell("date de la commande");
        table.addCell("préparé par");
        // table body
        int numberOfRow = lst_objects.size();
            for (int iRow = 0; iRow < numberOfRow; iRow++){
                table.addCell(lst_objects.get(iRow)[0].toString());
                table.addCell(lst_objects.get(iRow)[1].toString());
                table.addCell(lst_objects.get(iRow)[2].toString());
                table.addCell(lst_objects.get(iRow)[3].toString());

            };

        document.add(title);
        document.add(titleDate);
        document.add(table);

        document.close();
    }

}
