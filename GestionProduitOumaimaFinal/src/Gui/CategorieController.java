/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.MouseEvent;
import Services.CategorieService;
import entites.Categorie;
import entites.Produit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Imen
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Categorie> table_Categ;
    @FXML
    private TableColumn<Categorie , Integer> id;
    @FXML
    private TableColumn<Categorie, String> nom_aff;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    
    
    private HBox chosenhotelCard1;
    @FXML
    private TextField TFSearch;
    @FXML
    private TextField filterfield;

    /**
     * Initializes the controller class.
     */
    CategorieService cr= new CategorieService();
        
        List<Categorie> li =cr.realAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(li);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
TFSearch.textProperty().addListener((observable, oldValue, newValue) -> search());
              CategorieService cr= new CategorieService();
        
        List<Categorie> li =cr.realAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(li);
                  

        
          
       id.setCellValueFactory(
              new PropertyValueFactory<>("id"));
       nom_aff.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
       
     
        
       
        table_Categ.setItems(data);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
     if (nom.getText().isEmpty() ) {
             nom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                            int myIndex = table_Categ.getSelectionModel().getSelectedIndex();

             System.out.println("index :"+myIndex);

               System.out.println("Fields Are Empty");
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Fill your fields !");
                alert.show(); 
                
               
    }else{
        
        
        
        CategorieService cp =new CategorieService();
        
        cp.insert(new Categorie(nom.getText()));
        /*****/
          nom.setStyle("-fx-border-color: green ; -fx-border-width: 2px ;");
            
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                 alert.setHeaderText(null);
                alert.setContentText("ajouter avec succée !");
                alert.show(); 
          /****************************/
              
     
               
 List<Categorie> li =cp.realAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(li);
                  

        
          
       id.setCellValueFactory(
              new PropertyValueFactory<>("id"));
       nom_aff.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
       
     
        
       
        table_Categ.setItems(data);
        
          
       
 
    
                
    
     }
    }

    @FXML
    private void modifier(ActionEvent event) {
          CategorieService cp =new CategorieService();
          
          int myIndex = table_Categ.getSelectionModel().getSelectedIndex();
            System.out.println("index :"+myIndex);
             int idM = Integer.parseInt(String.valueOf(table_Categ.getItems().get(myIndex ).getId()));
        cp.update(new Categorie(idM,nom.getText()));
        /*****/
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                 alert.setHeaderText(null);
                alert.setContentText("Modifier avec succée !");
                alert.show();
                /*************/
                 List<Categorie> li =cp.realAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(li);
                  

        
          
       id.setCellValueFactory(
              new PropertyValueFactory<>("id"));
       nom_aff.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
       
     
        
       
        table_Categ.setItems(data);
    }

    @FXML
    private void supprimer(ActionEvent event) {
          CategorieService cp =new CategorieService();
          int myIndex = table_Categ.getSelectionModel().getSelectedIndex();
            System.out.println("index :"+myIndex);
             int idS = Integer.parseInt(String.valueOf(table_Categ.getItems().get(myIndex ).getId()));
          cp.delete(idS);
         
          /******/
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                 alert.setHeaderText(null);
                alert.setContentText("Supprimé avec succée !");
                alert.show();
                /*********************/
                 List<Categorie> li =cp.realAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(li);
                  

        
          
       id.setCellValueFactory(
              new PropertyValueFactory<>("id"));
       nom_aff.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
       
     
        
       
        table_Categ.setItems(data);
    }

    @FXML
    private void gestion_produit(ActionEvent event) {
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
 /*public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(cr.realAll());
            //System.out.println(data);
            FilteredList<Categorie> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
              
                    else if(String.valueOf(p.getNom()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Categorie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table_Categ.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table_Categ.setItems(sortedData);
             
 
    }*/
@FXML
        private void search() {
            String query = TFSearch.getText();
            ObservableList<Categorie> filteredList = FXCollections.observableArrayList();
                      CategorieService cp =new CategorieService();
            List<Categorie> li =cp.realAll();
        ObservableList<Categorie> data = FXCollections.observableArrayList(li);
            for (Categorie categorie : li) {
                if (categorie.getNom().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(categorie);
                }
            }
    table_Categ.setItems(filteredList);
    
}
}
 

