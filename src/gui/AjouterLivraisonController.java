/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.control.Alert;

import services.LivraisonService;
import services.LivreurService;

import entities.Livraison;
import entities.Livreur;
import entities.Commande;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizb
 */
public class AjouterLivraisonController implements Initializable {

    @FXML
    private ComboBox<Livreur> comboLivreur;
    @FXML
    private TextField nomDestination;
    @FXML
    private DatePicker dateLivraison;
    @FXML
    private ComboBox<Commande> comboCmd;
    @FXML
    private TextField adresseDestination;
    @FXML
    private TextField telephoneDestination;
    @FXML
    private Button retourr;
    @FXML
    private Button btn;
    private ListData listdata = new ListData();
    private List_Commande listCMD = new List_Commande();

    // public static final String ACCOUNT_SID = "ACf6fdb03518098cd1fd30281ba1a56f13";
    // public static final String AUTH_TOKEN = "55cf8e7a55d401a2a297ffe23fd8bee6"; 

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Livreur> observableLivreurs = FXCollections.observableArrayList();
        comboLivreur.setItems(listdata.getLivreurs());
        ObservableList<Commande> observableCommande = FXCollections.observableArrayList();
        comboCmd.setItems(listCMD.getCmd());
        btn.setOnAction(event -> {
            if (Saisi() == true) {
                try {
                    String nomDest = nomDestination.getText();
                    java.sql.Date dateLiv = java.sql.Date.valueOf(dateLivraison.getValue());
                    String adrDest = adresseDestination.getText();
                    String telDest = telephoneDestination.getText();
                    Commande commande = comboCmd.getValue();
                    Livreur livreur = comboLivreur.getValue();

                    if (livreur == null) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setHeaderText(null);
                        alert1.setContentText("veuiller choisir un livreur");
                        alert1.showAndWait();
                    }
                    LivraisonService l = LivraisonService.getInstance();
                    Livraison livraison = new Livraison(nomDest, dateLiv, adrDest, telDest, commande, livreur);
                    l.insert(livraison);
                    // taht star hedheye nzid il SMS 
                    //   sendsms();
                    //  sendSms("+21653661331", "Votre réclamation est  confirmée , consultez notre site ou notre application pour consultez le traitement");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("La livraison a été ajoutée avec succès.");
                    alert.showAndWait();

                    nomDestination.clear();
                    dateLivraison.setValue(null);
                    adresseDestination.clear();
                    telephoneDestination.clear();
                    comboLivreur.getSelectionModel().clearSelection();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }

        });
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("AfficherLivraison.fxml"));
        Stage window = (Stage) retourr.getScene().getWindow();
        window.setScene(new Scene(root3));
    }

    private boolean Saisi() {

        if (adresseDestination.getText().isEmpty() || nomDestination.getText().isEmpty() || telephoneDestination.getText().isEmpty()) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {
            return true;
        }

    }
    // public static void sendSms(String recipient, String messageBody) {

    //    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    //  Message message = Message.creator(
    //          new PhoneNumber(recipient), // To number
    //   new PhoneNumber("+16073501293"), // From number
    //  messageBody) // SMS body
    // .create();
    //     System.out.println("Message sent: " + message.getSid());
    //}
    public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

}
