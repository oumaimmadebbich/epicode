/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Services.ProduitService;
import com.itextpdf.text.pdf.PdfPCell;
import entites.Produit;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    @FXML
    private TextField quantite;
    @FXML
    private ComboBox<String> comb_categ;
    @FXML
    private ImageView imageView;
    @FXML
    private Button ajouter;
     private String pic;
    private Image image1;
    private Connection conx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<Produit, String> image;
    @FXML
    private TableColumn<Produit, Integer> id_categ;
    @FXML
    private TableView<Produit> table_Prod;
    @FXML
    private TableColumn<Produit, Integer> idP;
    @FXML
    private TableColumn<Produit, String> titreP;
    @FXML
    private TableColumn<Produit, Float> prixP;
    @FXML
    private TableColumn<Produit,String > descriptionP;
    @FXML
    private TableColumn<Produit, Integer> quantiteP;
    private TextField prix1;
    private TextField quantite1;
    private ComboBox<String> comb_categ1;
    private ImageView imageView1;
    @FXML
    private Button pdf;
    private ObservableList<Produit> listProduit;
    @FXML
    private Button codeQr;
    @FXML
    private ImageView codeqrr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            Connection cnx = MyConnection.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT Nom_categorie FROM categorie ");
            // Now add the comboBox addAll statement
            while (rs.next()){
               comb_categ.getItems().addAll(rs.getString("Nom_categorie"));
                //comb_categ1.getItems().addAll(rs.getString("Nom_categorie"));
                 
            }
        } catch (SQLException ex) {
           
        }
        
        /******************************Affichage********************************/
        
        ProduitService ps= new ProduitService();
        
        List<Produit> li =ps.realAll();
        ObservableList<Produit> data = FXCollections.observableArrayList(li);
                  

        
          
      idP.setCellValueFactory(
               new PropertyValueFactory<>("id"));
       titreP.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
 
       
     prixP.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
       descriptionP.setCellValueFactory(  
                new PropertyValueFactory<>("description")) ; 
        
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
          quantiteP.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));
          id_categ.setCellValueFactory(
                new PropertyValueFactory<>("categorie"));
         
          System.out.println(data);
        table_Prod.setItems(data);
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String titrePp=titre.getText();
    String prixPp = prix.getText();
    String descriptionPp= description.getText();
    String quantitePp = quantite.getText();
   
       

    if (titrePp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("titre doit etre saisi");
        titre.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else if (quantitePp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("quantite doit etre saisi");
        quantite.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else if (descriptionPp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setContentText("Description doit etre saisi");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else if (prixPp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        prix.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setContentText("Prix doit etre saisi");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } 
    else {
        try {
            Connection cnx = MyConnection.getInstance().getCnx();
              ResultSet rs6 = null;
             String v= String.valueOf(comb_categ.getValue());
                rs6 = cnx.createStatement().executeQuery("SELECT id_categorie FROM categorie where Nom_categorie='"+v+"'");  
     
                rs6.next();
               int id = rs6.getInt("id_categorie");
          
             int quantitep =Integer.parseInt(quantite.getText());
               float prixp = Float.parseFloat(prix.getText());

            // Open a file chooser to let the user select an image file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                // Read the bytes of the selected image file
               String imagePath = selectedFile.toPath().toString();
                
                /****/
                 pic=(selectedFile.toURI().toString());
                image1= new Image(pic);
           imageView.setImage(image1);
                /********/
Produit p= new Produit(titrePp,prixp,descriptionPp,imagePath,quantitep,id);
              
              ProduitService ps= new ProduitService();
                ps.insert(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Produit  inséré avec succès!");
                alert.showAndWait();
                
                /****************************/
               List<Produit> li =ps.realAll();
                            

                           
        ObservableList<Produit> data = FXCollections.observableArrayList(li);
                  

        
          
      idP.setCellValueFactory(
               new PropertyValueFactory<>("id"));
       titreP.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
 
       
     prixP.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
       descriptionP.setCellValueFactory(  
                new PropertyValueFactory<>("description")) ; 
        
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
          quantiteP.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));
          id_categ.setCellValueFactory(
                new PropertyValueFactory<>("categorie"));
         
          
        table_Prod.setItems(data);

        
          
      
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("ID  et prix Produit doivent etre des nombres");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
            
        }
        /*catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la lecture de l'image sélectionnée");
            alert.showAndWait();
        }*/
    }
        
    }

 
   
    @FXML
    private void supprimer(ActionEvent event) {
        
         int myIndex = table_Prod.getSelectionModel().getSelectedIndex();
            System.out.println("index :"+myIndex);
             int idS = Integer.parseInt(String.valueOf(table_Prod.getItems().get(myIndex ).getId()));
          ProduitService ps= new ProduitService();
          ps.delete(idS);
         
          /******/
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                 alert.setHeaderText(null);
                alert.setContentText("Supprimé avec succée !");
                alert.show();
                
                
                /************************/
                List<Produit> li =ps.realAll();
        ObservableList<Produit> data = FXCollections.observableArrayList(li);
                  

        
          
      idP.setCellValueFactory(
               new PropertyValueFactory<>("id"));
       titreP.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
 
       
     prixP.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
       descriptionP.setCellValueFactory(  
                new PropertyValueFactory<>("description")) ; 
        
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
          quantiteP.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));
          id_categ.setCellValueFactory(
                new PropertyValueFactory<>("categorie"));
         
          
        table_Prod.setItems(data);
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
          String titrePp=titre.getText();
    String prixPp = prix.getText();
    String descriptionPp= description.getText();
    String quantitePp = quantite.getText();
    
         int myIndex = table_Prod.getSelectionModel().getSelectedIndex();
            System.out.println("index :"+myIndex);
             int idS = Integer.parseInt(String.valueOf(table_Prod.getItems().get(myIndex ).getId()));
       

    if (titrePp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("titre doit etre saisi");
        titre.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else if (quantitePp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("quantite doit etre saisi");
        quantite.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else if (descriptionPp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setContentText("Description doit etre saisi");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } else if (prixPp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        prix.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        alert.setContentText("Prix doit etre saisi");
        alert.setTitle("Probleme");
        alert.setHeaderText(null);
        alert.showAndWait();
    } 
    else {
        try {
            Connection cnx = MyConnection.getInstance().getCnx();
              ResultSet rs6 = null;
             String v= String.valueOf(comb_categ.getValue());
                rs6 = cnx.createStatement().executeQuery("SELECT id_categorie FROM categorie where Nom_categorie='"+v+"'");  
     
                rs6.next();
               int id = rs6.getInt("id_categorie");
          
             int quantitep =Integer.parseInt(quantite.getText());
               float prixp = Float.parseFloat(prix.getText());

            // Open a file chooser to let the user select an image file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                // Read the bytes of the selected image file
                String imageBytes = Files.readAllBytes(selectedFile.toPath()).toString();
                
                /****/
                 pic=(selectedFile.toURI().toString());
                image1= new Image(pic);
           imageView.setImage(image1);
                /********/
Produit p= new Produit(idS,titrePp,prixp,descriptionPp,imageBytes,quantitep,id);
              
              ProduitService ps= new ProduitService();
                ps.update(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Produit  update avec succès!");
                alert.showAndWait();
                
                /****************************/
               List<Produit> li =ps.realAll();
        ObservableList<Produit> data = FXCollections.observableArrayList(li);
                  

        
          
      idP.setCellValueFactory(
               new PropertyValueFactory<>("id"));
       titreP.setCellValueFactory(
                new PropertyValueFactory<>("titre"));
 
       
     prixP.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
       descriptionP.setCellValueFactory(  
                new PropertyValueFactory<>("description")) ; 
        
        
        image.setCellValueFactory(
                new PropertyValueFactory<>("image"));
          quantiteP.setCellValueFactory(
                new PropertyValueFactory<>("quantite"));
          id_categ.setCellValueFactory(
                new PropertyValueFactory<>("categorie"));
         
          
        table_Prod.setItems(data);

        
          
      
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("ID  et prix Produit doivent etre des nombres");
            alert.setTitle("Probleme");
            alert.setHeaderText(null);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la lecture de l'image sélectionnée");
            alert.showAndWait();
        }
    }
    }     
     @FXML
    private void gestion_categorie(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Categorie.fxml"));
        
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
           
        }
    }
       
     @FXML
    private void stat(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Produitstat.fxml"));
        
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException ex) {
           
        }
    }

    @FXML
private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("table_produit.pdf"));
    document.open();

    PdfPTable pdfTable = new PdfPTable(7);

    // Add table headers
    pdfTable.addCell(new Phrase("Id"));
    pdfTable.addCell(new Phrase("Titre"));
    pdfTable.addCell(new Phrase("Prix"));
    pdfTable.addCell(new Phrase("Description"));
    pdfTable.addCell(new Phrase("Image"));
    pdfTable.addCell(new Phrase("Quantite"));
    pdfTable.addCell(new Phrase("Categorie"));

    // Get the list of products from the ProduitServices class
    ProduitService produitServices = new ProduitService();
    List<Produit> listProduit = produitServices.listProduit();

    // Add table rows
    for (Produit produit : listProduit) {
        pdfTable.addCell(new Phrase(Integer.toString(produit.getId())));
        pdfTable.addCell(new Phrase(produit.getTitre()));
        pdfTable.addCell(new Phrase(Float.toString(produit.getPrix())));
        pdfTable.addCell(new Phrase(produit.getDescription()));
       // pdfTable.addCell(new Phrase(produit.getImage()));
       //Image image=new Image(ImageDataFactory.create(produit.getImage()));
       com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(produit.getImage());
       pdfTable.addCell(image);
      
        pdfTable.addCell(new Phrase(Integer.toString(produit.getQuantite())));
        pdfTable.addCell(new Phrase(produit.getCategorie().getNom()));
    }

    document.add(pdfTable);
    document.close();
    Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler table_produit.pdf");
}

    @FXML
    private void qrcode(ActionEvent event) {
        Produit p = table_Prod.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "titre: "+p.getTitre()+"\n";
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            codeqrr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
    
    }
    }}  
    
    

