/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Demande;
import entities.HyperlinkTableCell;
import entities.Offre;
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
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;
import services.DemandeService;
import services.OffreService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AjouterDemandeController implements Initializable {
     OffreService fs = new OffreService();
     DemandeService ds =new DemandeService();
      public static Demande currentdemande;
    private static int i;

    @FXML
    private TableView<Demande> afficherD;
    @FXML
    private TableColumn<Demande, Integer> id;
    @FXML
    private TableColumn<Demande, String> etat;
    @FXML
    private TableColumn<Demande, DatePicker> datepostulation;
    @FXML
    private TableColumn<Demande, String> cv;
    @FXML
    private TableColumn<Demande, Integer> offre;
    @FXML
    private TextField cvD;
    @FXML
    private Button cvdemande;
    @FXML
    private ComboBox<Offre> nomoffre;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               List<Offre> listoffre = fs.afficherOffre();
          nomoffre.setItems(FXCollections.observableArrayList(listoffre));
		nomoffre.setConverter(new StringConverter<Offre>() {
			@Override
			public String toString(Offre object) {
				return object.getTitre();
			}

			@Override
			public Offre fromString(String string) {
				return nomoffre.getItems().stream().filter(a -> a.getTitre().equals(string)).findFirst().orElse(null);
			}
		});
        try {
         senddata();
     } catch (SQLException ex) {
         Logger.getLogger(GestionDemandesController.class.getName()).log(Level.SEVERE, null, ex);
     }
        afficherD.setEditable(true);
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
            afficherD.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id"));
             datepostulation.setCellValueFactory(new PropertyValueFactory("datepostulation"));
 cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
cv.setCellFactory(col -> new HyperlinkTableCell<>());
            etat.setCellValueFactory(new PropertyValueFactory("etat"));
           
      
           
            offre.setCellValueFactory(new PropertyValueFactory("offre_id"));
            offre.setCellFactory(col -> new TableCell<Demande, Integer>() {
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

    @FXML
    private void consulterproduit(ActionEvent event) {
    }

    @FXML
    private void Passercommande(ActionEvent event) {
    }

    @FXML
    private void consulterEvent(ActionEvent event) {
    }

    @FXML
    private void ConsulterOffre(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/AfficherOffre.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
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

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
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
        
          else{
              User userConnected = new User();
userConnected.setId(503);
Session.getInstance(userConnected);
User user = Session.getUser();

          java.sql.Date datee = new java.sql.Date(System.currentTimeMillis());
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
                 

                    //mail.sendMail(email,nom);
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
                        Demande d = afficherD.getSelectionModel().getSelectedItem();
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
    private void modifier(ActionEvent event) throws SQLException {
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
      
          else{
                               User userConnected = new User();
userConnected.setId(503);
Session.getInstance(userConnected);
User user = Session.getUser();

          java.sql.Date datee = new java.sql.Date(System.currentTimeMillis());
                
                  Demande d=new Demande(i,datee,cvD.getText(),user.getId(),nomoffre.getSelectionModel().getSelectedItem().getId());
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

                 
           }
    }
      private void choisirdemande(MouseEvent event) {
           Demande d = afficherD.getItems().get(afficherD.getSelectionModel().getSelectedIndex());
          this.i=d.getId();
        cvD.setText(d.getCv());
         }
      
    
}
