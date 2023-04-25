/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Image;
import entities.Demande;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
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

    @FXML
    private TextField cvD;

    @FXML
    private DatePicker dateD;

    @FXML
    private Button cvdemande;
    @FXML
    private ComboBox<Offre> nomoffre;
    @FXML
    private ImageView cvview;





     public static Demande currentdemande;
    private static int i;
    private TableColumn<Demande, Integer> colClient;
    private TableColumn<Demande,Integer> colOffre;
    @FXML
    private ComboBox<User> nomuser;


    @FXML
    private TableColumn<Demande, Integer> id;
    @FXML
    private TableColumn<Demande, String> etatt;
    @FXML
    private TableColumn<Demande, String> cv;
    @FXML
    private TableColumn<Demande, DatePicker> date;
    @FXML
    private TableColumn<Demande, Integer> clientt;
    @FXML
    private TableColumn<Demande, Integer> offree;
    @FXML
    private TableView<Demande> listedemande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

                List<User> listuser = us.afficherUser();
          nomuser.setItems(FXCollections.observableArrayList(listuser));
          nomuser.setConverter(new StringConverter<User>() {









             @Override
             public String toString(User object) {
                 return object.getNom();

             }

             @Override
             public User fromString(String string) {

           return nomuser.getItems().stream().filter(a -> a.getNom().equals(string)).findFirst().orElse(null);
             }
             });



                         senddata();
        listedemande.setEditable(true);
    }
          public void senddata()
{

    id.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("id"));
     etatt.setCellValueFactory(new PropertyValueFactory<Demande,String>("etat"));
    date.setCellValueFactory(new PropertyValueFactory<Demande,DatePicker>("datepostulation"));
    cv.setCellValueFactory(new PropertyValueFactory<Demande,String>("cv"));


    clientt.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("user_id"));
    offree.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("offre_id"));


    listedemande.setItems(FXCollections.observableArrayList( ds.afficherDemande()));
    ObservableList<Demande> oList = FXCollections.observableArrayList(ds.afficherDemande());
        FilteredList<Demande> filteredData = new FilteredList<Demande>(oList, b -> true);

        SortedList<Demande> sortedList = new SortedList <Demande>(filteredData);
        sortedList.comparatorProperty().bind(listedemande.comparatorProperty())    ;
        listedemande.setItems(sortedList);

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

    @FXML
    private void ajouterD(ActionEvent event) {
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
            Demande d=new Demande(datee,cvD.getText(),nomuser.getSelectionModel().getSelectedItem().getId(),nomoffre.getSelectionModel().getSelectedItem().getId());
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
                String email = nomuser.getSelectionModel().getSelectedItem().getEmail();
                    String nom = nomuser.getSelectionModel().getSelectedItem().getNom();
                    String prenom = nomuser.getSelectionModel().getSelectedItem().getPrenom();

                    mail.sendMail(email,nom,prenom);
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

    @FXML
    private void modifierD(ActionEvent event) {
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



    @FXML
    private void supprimerD(ActionEvent event) {

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
    }



