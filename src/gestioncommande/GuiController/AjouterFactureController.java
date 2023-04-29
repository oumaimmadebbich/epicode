/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestioncommande.GuiController;

import gestioncommande.Entity.Commande;
import gestioncommande.Entity.Facture;
import gestioncommande.Service.CommandeSerivce;
import gestioncommande.Service.FactureService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sabri
 */
public class AjouterFactureController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_edit;
    @FXML
    private TableView<Commande> CommandeTab;
    @FXML
    private TableColumn<Commande, Integer> IDCommandeTab;
    @FXML
    private TableColumn<Commande, Integer> ProduitTab;
    @FXML
    private TableColumn<Commande, Integer> ClientTab;
    @FXML
    private TableColumn<Commande, Integer> QuantityTab;
    @FXML
    private TableColumn<Commande, Float> MontantTab;
    @FXML
    private TableColumn<Commande, LocalDateTime> DateTab;
    @FXML
    private Button Statistique1;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    CommandeSerivce cs =new CommandeSerivce();
    FactureService fs =new FactureService();
    int id;
    Commande c;
    ObservableList<Commande> data=FXCollections.observableArrayList();
    @FXML
    private Button btn_facture;
    @FXML
    private Label Maxfacture;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            Maxfacture.setText(fs.getFactureWithMaxPrice().toString());
        } catch (SQLException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }    
    @FXML
    private void fillforum(MouseEvent event) {
        Commande commande=CommandeTab.getSelectionModel().getSelectedItem();
        id=commande.getIdcmd();
    }
    @FXML
    private void AjouterFacture(ActionEvent event) throws SQLException {
                String erreurs="";
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
        Facture facture = new Facture(id,
                                                 date.getValue().atStartOfDay());
         fs.AjouterFacture(id,facture);        

    } 
    }
    public void refreshlist(){
        data.clear();
        try {
            data=FXCollections.observableArrayList(cs.afficher());
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IDCommandeTab.setVisible(false);
        IDCommandeTab.setCellValueFactory(new PropertyValueFactory<>("idcmd"));
        ProduitTab.setCellValueFactory(new PropertyValueFactory<>("idP"));
        ClientTab.setCellValueFactory(new PropertyValueFactory<>("idCl"));
        QuantityTab.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        MontantTab.setCellValueFactory(new PropertyValueFactory<>("montant"));
        DateTab.setCellValueFactory(new PropertyValueFactory<>("datecmd"));
        CommandeTab.setItems(data);
    }

    @FXML
    private void Retour(ActionEvent event) {
                     try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gestioncommande/Gui/Menu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void TéléchargerFacture(ActionEvent event) {
        new FactureService().exporterPDF();
            
    }
    
}
