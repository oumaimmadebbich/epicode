/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
/**
 * FXML Controller class
 *
 * @author ZC-Lenovo
 */
public class TestpdfController implements Initializable {

    @FXML
    private ImageView pdf;
     
    
    private static final String filepath = "‪‪codeqrr.png";
   

    private PDDocument document;
    private PDFRenderer renderer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            // Load the PDF document
            File file = new File("C:\\Users\\ZC-Lenovo\\Desktop\\Javaproject\\src\\javaproject\\janvier2022.pdf");
            document = Loader.loadPDF(file);

            // Create a PDF renderer
            renderer = new PDFRenderer(document);

            // Render the first page
            PDPage page = document.getPage(0);

          BufferedImage image = renderer.renderImageWithDPI(0, 600, ImageType.RGB);


            // Convert the buffered image to a JavaFX image
            Image fxImage = SwingFXUtils.toFXImage(image, null);

            // Set the JavaFX image to the image view
            pdf.setImage(fxImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }    
   
}
