package be.atc.controler.pdf;

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
    public static void createPdf(String destination, List<Object[]> lst_objects)
            throws FileNotFoundException, DocumentException, DocumentException {
        Document document = new Document();
        FileOutputStream file = new FileOutputStream(destination);
        PdfWriter.getInstance(document,file);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");

        document.open();
        // Creation de 2 paragraphes et mise en page
        Paragraph title = new Paragraph("Liste des commandes fournisseur en cours");
        Paragraph titleDate = new Paragraph("Date : "+ dateFormat.format(new Date()));
        title.setSpacingAfter(20);
        titleDate.setSpacingAfter(10);

        // Creation d'une table
        int numberOfColumns = 7;
        PdfPTable table = new PdfPTable(numberOfColumns); // Tout les colonnes doivent être remplis
        table.addCell("# Commande");
        table.addCell("Fournisseur");
        table.addCell("date de la commande");
        table.addCell("Produit");
        table.addCell("Qté");
        table.addCell("Lot fournisseur");
        table.addCell("préparé par");

        int numberOfRow = lst_objects.size();
            for (int iRow = 0; iRow < numberOfRow; iRow++){
                table.addCell(lst_objects.get(iRow)[0].toString());
                table.addCell(lst_objects.get(iRow)[1].toString());
                table.addCell(lst_objects.get(iRow)[2].toString());
                table.addCell(lst_objects.get(iRow)[3].toString());
                table.addCell(lst_objects.get(iRow)[4].toString());
                table.addCell(lst_objects.get(iRow)[5].toString());
                table.addCell(lst_objects.get(iRow)[6].toString());
            };

        document.add(title);
        document.add(titleDate);
        document.add(table);

        document.close();
    }

}
