/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.LivreurService;

import entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizb
 */
public class AjouterLivreurController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField secteur;
    @FXML
    private TextField telephone;
    @FXML
    private TextField password;
    @FXML
    private TextField type_livreur;

    @FXML
    private Button retourr;
    public static final String ACCOUNT_SID = "ACf6fdb03518098cd1fd30281ba1a56f13";
    public static final String AUTH_TOKEN = "55cf8e7a55d401a2a297ffe23fd8bee6";
    @FXML
    private AnchorPane left_main;
    @FXML
    private Button access_profil;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn.setOnAction(event -> {
            if (Saisi() == true) {
                Livreur p = new Livreur(nom.getText(), prenom.getText(), secteur.getText(), telephone.getText(), password.getText(), type_livreur.getText());
                LivreurService l = LivreurService.getInstance();
                int id = l.insertProfile(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Votre ID");
                alert.setHeaderText(null);
                alert.setContentText("Votre ID est " + id + ". N'oubliez pas de le noter.");
                alert.show();
                nom.setText("");
                prenom.setText("");
                secteur.setText("");
                telephone.setText("");
                password.setText("");
                type_livreur.setText("");
            }

        });
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) retourr.getScene().getWindow();
        window.setScene(new Scene(root3));
    }
        @FXML
    private void Profile(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("AfficherLivreur.fxml"));
        Stage window = (Stage) access_profil.getScene().getWindow();
        window.setScene(new Scene(root3));
    }

    private boolean Saisi() {

        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || secteur.getText().isEmpty() || telephone.getText().isEmpty() || password.getText().isEmpty() || type_livreur.getText().isEmpty()) {
            Alert(Alert.AlertType.ERROR, "Données invalides", "Verifier !!", "Veuillez bien remplir tous les champs !");
            return false;
        } else {
            return true;
        }

    }

    public static void Alert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

}
