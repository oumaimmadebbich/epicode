/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author azizb
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Livraison;
import gui.List_Livraison;
import entities.Livraison;

import entities.Livreur;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PDFGenerator1 {

    List<Livraison> list = new ArrayList<>();

    public void GeneratePdf(String filename, List<Livraison> list) throws FileNotFoundException, DocumentException,
            BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };

        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();
        int i = 1;
        for (Livraison Livraison : list) {

            document.add(new Paragraph("La Livraison N°" + i + " Associé a vous est"));
            document.add(new Paragraph("L'adress de destination est:" + Livraison.getAdresse_destination()));
            document.add(new Paragraph("nom de destination est =:" + Livraison.getNom_destination()));
            document.add(new Paragraph("La date de livraison est =:" + Livraison.getDate_livraison()));
            document.add(new Paragraph("          "));

            document.add(new Paragraph("*********************************************************"));

            i = i + 1;
        }
        document.add(new Paragraph("                              Welcome to ReUsed                    "));

        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }


}
