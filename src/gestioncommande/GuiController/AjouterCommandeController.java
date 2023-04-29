/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestioncommande.GuiController;

import gestioncommande.Entity.Commande;
import gestioncommande.Service.CommandeSerivce;
import gestioncommande.Utils.Myconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Integer> cb_Produit;
    ObservableList<Integer> optionsProduit=FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> cb_Client;
    ObservableList<Integer> optionsClient=FXCollections.observableArrayList();
    @FXML
    private TextField TfQuantity;
    @FXML
    private TextField TfMontant;

    /**
     * Initializes the controller class.
     */
    CommandeSerivce cs =new CommandeSerivce();
    int id;
    Commande c;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillcomboProduit();
        fillcomboClient();
    }    
    public void fillcomboClient(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from client";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsClient.add(rs.getInt("idCl"));
            }
            cb_Client.setItems(optionsClient);
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void fillcomboProduit(){
        try {
            Connection cnx = Myconnexion.getInstance().getCnx();
            String req = " select * from produit";
            PreparedStatement cs = cnx.prepareStatement(req);
            ResultSet rs = cs.executeQuery(req);
            while(rs.next()){
                optionsProduit.add(rs.getInt("idP"));
            }
            cb_Produit.setItems(optionsProduit);
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Acceuil(ActionEvent event) {
                     try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gestioncommande/Gui/GestionCommande.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void Enregister(ActionEvent event) {
                String erreurs="";
        if(TfQuantity.getText().trim().isEmpty()){
            erreurs+="Quantity vide\n";
        }
        if(TfMontant.getText().trim().isEmpty()){
            erreurs+="Montant vide\n";
        }
        if(  date.getValue() != null
             &&   date.getValue().isBefore(LocalDate.now())
               ){
            erreurs+="date must be after\n";
        } 
        if(date.getValue() == null){
            erreurs+="date vide\n";
        } 
        
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout Reclamation");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
        Commande commande = new Commande(cb_Produit.getValue(),
                                                 cb_Client.getValue(), 
                                                 Integer.parseInt(TfQuantity.getText()),
                                                 Float.parseFloat(TfMontant.getText()),
                                                 date.getValue().atStartOfDay());
                  cs.ajouter(commande);        

    }
    }
    
}
