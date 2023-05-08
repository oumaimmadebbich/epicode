/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.ProduitService;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oumaima debbich
 */
public class AfficherproduitController implements Initializable {

    
    ProduitService Ps=new ProduitService();
    
    
    @FXML
    private GridPane gridEvent;
    @FXML
    private Button categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            afficherProduit();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
       

    public void afficherProduit() throws SQLException{
         try {
            List<Produit> Produits = Ps.recupererProduit();
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < Produits.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("produits.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ProduitsController controller = loader.getController();
                controller.setProduit(Produits.get(i));
                controller.setidProd(Produits.get(i).getId());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void categorie(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Produit.fxml"));
        
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
    private void Retour(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        
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

    
}
