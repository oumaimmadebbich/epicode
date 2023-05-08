/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXButton;
import entities.Offre;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.OffreService;
import services.PDFGenerator;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class GestionOffresController implements Initializable {
    PDFGenerator pdf = new PDFGenerator(); 

  OffreService os = new OffreService();
    @FXML
    private TextField titreO;
    @FXML
    private TextField descriptionO;
    @FXML
    private TextField experienceO;
    @FXML
    private DatePicker dateO;
    @FXML
    private TableView<Offre> afficherO;
    @FXML
    private TableColumn<Offre, Integer> colID;
    @FXML
    private TableColumn<Offre, String> colTitre;
    @FXML
    private TableColumn<Offre, DatePicker> colDate;
    @FXML
    private TableColumn<Offre, String> colDescription;
    @FXML
    private TableColumn<Offre, String> colExperience;
    public static Offre currentoffre;
    private static int i;
    @FXML
    private JFXButton pdff;
    @FXML
    private JFXButton qrcode;
    @FXML
    private ImageView qrcodee;
    @FXML
    private TextField tfrecherche;
    
    ObservableList<Offre> data=FXCollections.observableArrayList();
    @FXML
    private HBox chosenhotelCard1;
    @FXML
    private Button Statistique11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          senddata();
        afficherO.setEditable(true);
        try {
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
      public void senddata()
{
    colID.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id"));
    colTitre.setCellValueFactory(new PropertyValueFactory<Offre,String>("titre"));
    colDate.setCellValueFactory(new PropertyValueFactory<Offre,DatePicker>("datelimite"));
   colDescription.setCellValueFactory(new PropertyValueFactory<Offre,String>("description"));
    
    
    colExperience.setCellValueFactory(new PropertyValueFactory<Offre,String>("experience"));
  
    afficherO.setItems(FXCollections.observableArrayList( os.afficherOffre()));
    ObservableList<Offre> oList = FXCollections.observableArrayList(os.afficherOffre());
        FilteredList<Offre> filteredData = new FilteredList<Offre>(oList, b -> true);
        
        SortedList<Offre> sortedList = new SortedList <Offre>(filteredData);
        sortedList.comparatorProperty().bind(afficherO.comparatorProperty())    ;
        afficherO.setItems(sortedList);
    
}

private boolean NoDate() {
         LocalDate currentDate = LocalDate.now();     
         LocalDate myDate = dateO.getValue(); 
         int comparisonResult = myDate.compareTo(currentDate);      
         boolean test = true;
        if (comparisonResult < 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult > 0) {
         // myDate est postérieure à currentDate
         test = false;
        }
        return test;
    }
    @FXML
    private void ajouterO(ActionEvent event) {
             if ((titreO.getText().length()==0)||(descriptionO.getText().length()==0) || (experienceO.getText().length()==0))
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    
                    alert.showAndWait();}
         
             else if ((titreO.getText().length() <5 )||(descriptionO.getText().length()<5) || (experienceO.getText().length()<5)) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Please enter a fields with a minimum 5 characters.");
        alert.showAndWait();
        return;
    }  
          else if (NoDate()== true){
                   Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText(" datelimite should be greater than today");
                    alert.showAndWait();
                    
         }
          else{
          java.sql.Date datee = java.sql.Date.valueOf(dateO.getValue());
            Offre f=new Offre(titreO.getText(),datee,descriptionO.getText(),experienceO.getText());
    
            os.ajouterOffre(f);
            Notifications notificationBuilder = Notifications.create()
        .title("Offre ajouté")
        .text("votre Offre a été ajouté avec succes")
        .graphic(null)
        
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajouté avec succes");
            }
               });
        notificationBuilder.showConfirm();
         senddata();
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event add");
            alert.setContentText("Event added successfully!");
            alert.showAndWait();
            titreO.clear();
              
               descriptionO.clear();
                experienceO.clear();
                 dateO.setValue(null);
         }
    }
         
    

    @FXML
    private void modifierO(ActionEvent event) {
            
         if ((titreO.getText().length()==0)||(descriptionO.getText().length()==0) ||(experienceO.getText().length()==0))
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");
                    
                    alert.showAndWait();}
         
         else if ((titreO.getText().length() < 5)||(descriptionO.getText().length()<5) ||(experienceO.getText().length()==0)  ) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Please enter a title with a length of maximum 5 characters.");
        alert.showAndWait();
    }  
           else if (NoDate()== true){
                   Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText(" date limite should be greater than today");
                    alert.showAndWait();
         }
         else
           {
                java.sql.Date datee = java.sql.Date.valueOf(dateO.getValue());
                  Offre f=new Offre(i,titreO.getText(),datee,descriptionO.getText(),experienceO.getText());
            // (int idEv, String nomEv, String localisation, String dateDEv, String dateFEv, String image) 
         os.modifieroffre(f);
         Notifications notificationBuilder = Notifications.create()
        .title("Offre modifié")
        .text("votre Offre a été modifié avec succes")
        .graphic(null)
        
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("modifié avec succes");
            }
               });
        notificationBuilder.showConfirm();
          senddata();
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Offre edit");
            alert.setContentText("Offred edit successfully!");
            alert.showAndWait();
             titreO.clear();
              
               descriptionO.clear();
                experienceO.clear();
                 dateO.setValue(null);
           }
    }

    @FXML
    private void supprimerO(ActionEvent event) {
          Offre o = afficherO.getSelectionModel().getSelectedItem();
        os.supprimerOffre(o.getId());
        senddata();
        Notifications notificationBuilder = Notifications.create()
        .title("Offre supprimer")
        .text("votre Offre a été supprimer avec succes")
        .graphic(null)
        
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("supprimer avec succes");
            }
               });
        notificationBuilder.showConfirm();
        senddata();
    }
       
       
    


  
     private void populateTable(ObservableList<Offre> branlist){
       afficherO.setItems(branlist);
    }

    @FXML
    private void choisirOffre(MouseEvent event) {
         Offre f = afficherO.getItems().get(afficherO.getSelectionModel().getSelectedIndex());
          this.i=f.getId();
        titreO.setText(f.getTitre());
        descriptionO.setText(f.getDescription());
        experienceO.setText(f.getExperience());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse((CharSequence) f.getDatelimite());
   
       dateO.setValue(localDate);
      
       
       
    }

    @FXML
    private void exportpdf(ActionEvent event) {
         if(afficherO.getSelectionModel().getSelectedItem()!= null){
        Offre O = afficherO.getSelectionModel().getSelectedItem();
        
            try {
                try {
					pdf.GeneratePdf("Offre", O);
				} catch (BadElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } catch (IOException ex) {
                Logger.getLogger(GestionOffresController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestionOffresController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GestionOffresController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}

    @FXML
    private void Onqrcode(ActionEvent event) {
         Offre o = afficherO.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "titre: "+o.getTitre()+"\n"+"Experience : "+o.getExperience()+"\n"+"Datelimite : "+o.getDatelimite()+"\n"+"Description :"+o.getDescription();
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
            
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
    }
}

    @FXML
    private void OnStats(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
Parent root = loader.load();
Scene scene = new Scene(root);
Stage stage = new Stage();
stage.setTitle("Stats");
stage.setScene(scene);
stage.show();
    }
       public void recherche_avance() throws SQLException{
        //remplire lobservablelist
        data.clear();
        data.addAll(os.afficherOffre());
        //liste filtrer
        FilteredList<Offre> filtreddata;
        filtreddata = new FilteredList<>(data,r->true);
        //creation du listenere a partire du textfield
        tfrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filtreddata.setPredicate(Offre->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
              
                if(Offre.getTitre().indexOf(newValue)!=-1){
                    return true;
                }
                else if(Offre.getDescription().indexOf(newValue)!=-1){
                    return true;
                }
                else if(Offre.getExperience().indexOf(newValue)!=-1){
                    return true;
                }
               
                
                
                else{
                    return false;
                }
            });
            afficherO.setItems(filtreddata);
        });
}

    @FXML
    private void gestion_Demandes(ActionEvent event) {
         
         try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/GestionDemandes.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    



    @FXML
    private void retour(ActionEvent event) {
                   try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/dash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    
}
