/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import entities.HyperlinkTableCell;
import entities.Offre;
import entities.PDFTableCell;
import entities.User;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import utils.Session;
import org.controlsfx.control.Notifications;
import services.DemandeService;
import services.Mailservice;
import services.OffreService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class GestionDemandesController implements Initializable {
 DemandeService ds = new DemandeService();
 OffreService fs = new OffreService();
  UserService us = new UserService();
  Mailservice mail = new Mailservice();

    private TextField cvD;

    private DatePicker dateD;

    private ComboBox<Offre> nomoffre;
    @FXML
    private ImageView cvview;
    
    
       ObservableList<Demande> data=FXCollections.observableArrayList();





     public static Demande currentdemande;
    private static int i;
    private TableColumn<Demande, Integer> colClient;
    private TableColumn<Demande,Integer> colOffre;
    private ComboBox<User> nomuser;


    @FXML
    private TableColumn<Demande, Integer> id;
    @FXML
    private TableColumn<Demande, String> etatt;
    @FXML
    private TableColumn<Demande, String> cv;
    @FXML
    private TableColumn<Demande, String> date;
    @FXML
    private TableColumn<Demande, Integer> clientt;
    @FXML
    private TableColumn<Demande, Integer> offree;
    @FXML
    private TableView<Demande> listedemande;
    @FXML
    private HBox chosenhotelCard1;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         senddata();
     } catch (SQLException ex) {
         Logger.getLogger(GestionDemandesController.class.getName()).log(Level.SEVERE, null, ex);
     }
        listedemande.setEditable(true);
    }
          public void senddata() throws SQLException
{
//
//    id.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("id"));
//     etatt.setCellValueFactory(new PropertyValueFactory<Demande,String>("etat"));
//    date.setCellValueFactory(new PropertyValueFactory<Demande,DatePicker>("datepostulation"));
//    cv.setCellValueFactory(new PropertyValueFactory<Demande,String>("cv"));
//
//
//    clientt.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("user_id"));
//    offree.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("offre_id"));
//
//
//    listedemande.setItems(FXCollections.observableArrayList( ds.afficherDemande()));
//    ObservableList<Demande> oList = FXCollections.observableArrayList(ds.afficherDemande());
//        FilteredList<Demande> filteredData = new FilteredList<Demande>(oList, b -> true);
//
//        SortedList<Demande> sortedList = new SortedList <Demande>(filteredData);
//        sortedList.comparatorProperty().bind(listedemande.comparatorProperty())    ;
//        listedemande.setItems(sortedList);
                  
                 
            // TODO
            List<Demande> evenements = ds.afficherDemande();
            ObservableList<Demande> olp = FXCollections.observableArrayList(evenements);
            listedemande.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id"));
             date.setCellValueFactory(new PropertyValueFactory("datepostulation"));
 cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
cv.setCellFactory(col -> new HyperlinkTableCell<>());
            etatt.setCellValueFactory(new PropertyValueFactory("etat"));
           
      
            clientt.setCellValueFactory(new PropertyValueFactory("user_id"));
                   clientt.setCellFactory(col -> new TableCell<Demande, Integer>() {
             @Override
    protected void updateItem(Integer user_id, boolean empty) {
        super.updateItem(user_id, empty);
        if (empty) {
            setText(null);
        } else {
            UserService os=new UserService();
            User u = os.afficher_user(user_id);
            setText(u.getNom());
        }
    }
                   });
            offree.setCellValueFactory(new PropertyValueFactory("offre_id"));
            offree.setCellFactory(col -> new TableCell<Demande, Integer>() {
    @Override
    protected void updateItem(Integer offre_id, boolean empty) {
        super.updateItem(offre_id, empty);
        if (empty) {
            setText(null);
        } else {
            OffreService os=new OffreService();
            Offre o = os.afficher1Offre(offre_id);
            setText(o.getTitre());
        }
    }
});
           // this.delete();
  

}
    private boolean NoDate() {
         LocalDate currentDate = LocalDate.now();
         LocalDate myDate = dateD.getValue();
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

    private void ajouterD(ActionEvent event) throws SQLException, SQLException {
               if ((cvD.getText().length()==0) )
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");

                    alert.showAndWait();}

             else if ((cvD.getText().length() <5 )    ) {
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
              User userConnected = new User();
userConnected.setId(503);
Session.getInstance(userConnected);
User user = Session.getUser();

          java.sql.Date datee = java.sql.Date.valueOf(dateD.getValue());
            Demande d=new Demande(datee,cvD.getText(),user.getId(),nomoffre.getSelectionModel().getSelectedItem().getId());
    System.out.println(d);
           ds.ajouterDemande(d);


         //senddata();
          //FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayEvents.fxml"));
          Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Offre added");
            alert.setContentText("Offre added successfully!");
            alert.showAndWait();
            cvD.clear();
            // System.out.println(this.chosenProduct.getLibelle());



               // id_user.clear();

                 dateD.setValue(null);
         }
               Notifications notificationBuilder = Notifications.create()
        .title("Demande ajoutée")
        .text("votre demande a été ajoutée avec succes")
        .graphic(null)

        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajoutée avec succes");
            }
               });
        notificationBuilder.showConfirm();
               senddata();
                             User userConnected = new User();
userConnected.setId(1);
Session.getInstance(userConnected);
User user = Session.getUser();
                String email = user.getEmail();
                    String nom = user.getNom();
                 

                  //  mail.sendMail(email,nom);
    }

    private void uploadpdf(ActionEvent event) throws FileNotFoundException, IOException {

         Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.pdf"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\ImageP\\\\"  + x + ".pdf";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
           javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toString());
            //cvview.setImage(img);
            cvD.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
        } else {
            System.out.println("error");
        }
    }

    private void choisirdemande(MouseEvent event) {
           Demande d = listedemande.getItems().get(listedemande.getSelectionModel().getSelectedIndex());
          this.i=d.getId();
        cvD.setText(d.getCv());

        //id_user.setText(String.valueOf(d.getUser()));
       // String path = d.getCv();
              // File file=new File(path);
               //Image img = new Image(file.toURI().toString());
                //cvview.setImage(img);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse((CharSequence) d.getDatepostulation());

       dateD.setValue(localDate);

    }

    private void modifierD(ActionEvent event) throws SQLException {
                       if ((cvD.getText().length()==0) )
                { Alert alert = new Alert(AlertType.ERROR);
                   alert.setTitle("Error ");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Fields cannot be empty");

                    alert.showAndWait();}

             else if ((cvD.getText().length() <5 )    ) {
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
                java.sql.Date datee = java.sql.Date.valueOf(dateD.getValue());
                  Demande d=new Demande(i,datee,cvD.getText(),nomuser.getSelectionModel().getSelectedItem().getId(),nomoffre.getSelectionModel().getSelectedItem().getId());
            // (int idEv, String nomEv, String localisation, String dateDEv, String dateFEv, String image)
         ds.modifierdemande(d);
         Notifications notificationBuilder = Notifications.create()
        .title("Demande modifiée")
        .text("votre demande a été modifiée avec succes")
        .graphic(null)

        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("modifiée avec succes");
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
             cvD.clear();


               // id_user.clear();

                 dateD.setValue(null);
           }
          }



    private void supprimerD(ActionEvent event) throws SQLException {

                  Demande d = listedemande.getSelectionModel().getSelectedItem();
        ds.supprimerDemande(d.getId());
        senddata();
        Notifications notificationBuilder = Notifications.create()
        .title("Demande supprimer")
        .text("votre Demande a été supprimer avec succes")
        .graphic(null)

        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("supprimer avec succes");
            }
               });
        notificationBuilder.showConfirm();
}

    @FXML
    private void gestion_offres(ActionEvent event) {
         
         try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/GestionOffres.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void repondre(ActionEvent event) throws SQLException {
              Demande d = listedemande.getSelectionModel().getSelectedItem();
              System.out.println(d);
              if(d.getEtat()!="Traité")
              {
              ds.traiterDemande(d.getId());
                 senddata();
        Notifications notificationBuilder = Notifications.create()
        .title("Demande traité")
        .text("la Demande a été traité avec succes")
        .graphic(null)

        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("supprimer avec succes");
            }
               });
        notificationBuilder.showConfirm();
      int id_user=  d.getUser_id();
     User u = us.afficher_user(id_user);
     String email=u.getNom();
     System.out.println(email);
         String nom = u.getNom();
                 

                    mail.sendMail(email,nom);
     
      
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
    



