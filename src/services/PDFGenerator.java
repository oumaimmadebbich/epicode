package services;

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

import entities.Offre;

public class PDFGenerator {

	Offre o = new Offre();

	public void GeneratePdf(String filename, Offre o) throws FileNotFoundException, DocumentException,
			BadElementException, IOException, InterruptedException, SQLException {

		Document document = new Document() {
		};
		PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
		document.open();
                Image logo = Image.getInstance("C:/xampp/htdocs/uploads/logo.jpg");
                 logo.scaleToFit(80, 80);
                 document.add(logo);


		document.add(new Paragraph("Titre de l'offre:" + o.getTitre()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph(
				""));

		
		
		
		
		document.add(new Paragraph("Description de l'offre:" + o.getDescription()));
		document.add(new Paragraph("                      "));
		
		
		
		document.add(new Paragraph("Experience :" + o.getDescription()));
		document.add(new Paragraph("                      "));
                
		document.add(new Paragraph(" Lieur du poste: Ariana                     "));
                document.add(new Paragraph(" Avantages sociaux :                   "));
                 document.add(new Paragraph(" -Le ticket restaurant                  "));
                  document.add(new Paragraph(" -La complémentaire de santé                 "));
                    document.add(new Paragraph(" -Les bons dachats ou réductions spéciales                 "));
                    document.add(new Paragraph("                      "));
                    document.add(new Paragraph("                      "));
                    document.add(new Paragraph(" Profil recherché :               "));
                     document.add(new Paragraph("    Qualité recherchés : Dynamisme  Esprit d'équipe  Bon elationnel et sens de la communication           "));
                      document.add(new Paragraph("    Comment postuler ??          "));
                       document.add(new Paragraph("  Coordonées:Email       "));
                       document.add(new Paragraph(" Date limite pour postuler :" +o.getDatelimite()));
                       
                       
                      
                    
                    
                    
                    
                  
               
                

		document.add(new Paragraph(
				""));
		document.add(new Paragraph("                              Welcome to ReUsed                    "));
		document.close();
		Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
	}
}