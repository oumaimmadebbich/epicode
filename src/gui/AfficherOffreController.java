/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherOffreController implements Initializable {
 OffreService os = new OffreService();
    @FXML
    private TableView<Offre> afficherO;
    @FXML
    private TableColumn<Offre, Integer> id;
    @FXML
    private TableColumn<Offre, String> titre;
    @FXML
    private TableColumn<Offre, DatePicker> datelimite;
    @FXML
    private TableColumn<Offre, String> description;
    @FXML
    private TableColumn<Offre, String> experience;
    
        ObservableList<Offre> data=FXCollections.observableArrayList();
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        senddata();
     try {
         recherche_avance();
     } catch (SQLException ex) {
         Logger.getLogger(AfficherOffreController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    

    @FXML
    private void consulterproduit(ActionEvent event) {
                 try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/afficherproduit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void Passercommande(ActionEvent event) {
                 try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/FXML1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void consulterEvent(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/FXML1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    

    @FXML
    private void ConsulterOffre(ActionEvent event) {
    }

    @FXML
    private void postuler(ActionEvent event) {
           try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/AjouterDemande.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public void senddata()
{
    id.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id"));
    titre.setCellValueFactory(new PropertyValueFactory<Offre,String>("titre"));
    datelimite.setCellValueFactory(new PropertyValueFactory<Offre,DatePicker>("datelimite"));
   description.setCellValueFactory(new PropertyValueFactory<Offre,String>("description"));
    
    
    experience.setCellValueFactory(new PropertyValueFactory<Offre,String>("experience"));
  
    afficherO.setItems(FXCollections.observableArrayList( os.afficherOffre()));
    ObservableList<Offre> oList = FXCollections.observableArrayList(os.afficherOffre());
        FilteredList<Offre> filteredData = new FilteredList<Offre>(oList, b -> true);
        
        SortedList<Offre> sortedList = new SortedList <Offre>(filteredData);
        sortedList.comparatorProperty().bind(afficherO.comparatorProperty())    ;
        afficherO.setItems(sortedList);
    
}
            public void recherche_avance() throws SQLException{
        //remplire lobservablelist
        data.clear();
        data.addAll(os.afficherOffre());
        //liste filtrer
        FilteredList<Offre> filtreddata;
        filtreddata = new FilteredList<>(data,r->true);
        //creation du listenere a partire du textfield
        recherche.textProperty().addListener((observable,oldValue,newValue)->{
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

    
}
