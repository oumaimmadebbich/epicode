/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestioncommande.GuiController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Commande(ActionEvent event) {
                     try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gestioncommande/Gui/AjouterCommande.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void Facture(ActionEvent event) {
                     try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gestioncommande/Gui/AjouterFacture.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
