/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestioncommande.GuiController;

import com.jfoenix.controls.JFXComboBox;
import gestioncommande.Entity.Commande;
import gestioncommande.Service.CommandeSerivce;
import gestioncommande.Utils.Myconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Homrani
 */
public class GestionCommandeController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button btn_delete;
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
    private TextField TFSearch;
    @FXML
    private Button Statistique1;
    @FXML
    private TextField TfQuantity;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Integer> cb_Produit;
    ObservableList<Integer> optionsProduit=FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> cb_Client;
    ObservableList<Integer> optionsClient=FXCollections.observableArrayList();
    @FXML
    private TextField TfMontant;

    /**
     * Initializes the controller class.
     */
    
    CommandeSerivce cs =new CommandeSerivce();
    int id;
    Commande c;
    ObservableList<Commande> data=FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         refreshlist();
         fillcomboProduit();
         fillcomboClient();
        try {
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void recherche_avance() throws SQLException {
          
                  data = FXCollections.observableArrayList(cs.afficher());
            //System.out.println(data);
            FilteredList<Commande> filteredData = new FilteredList<>(data, b -> true);
            TFSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(p.getIdcmd()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; 
                    } 
                    if (String.valueOf(p.getIdP()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(String.valueOf(p.getIdCl()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(String.valueOf(p.getQuantity()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(String.valueOf(p.getMontant()).toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                    }
                    else if(String.valueOf(p.getDatecmd()).toLowerCase().indexOf(lowerCaseFilter)!=-1){
                    return true;
                    }
                    
                    else
                        return false; // Does not match.
                });
                
            });
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CommandeTab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CommandeTab.setItems(sortedData);
             
        }

    @FXML
    private void fillforum(MouseEvent event) {
        Commande commande=CommandeTab.getSelectionModel().getSelectedItem();
        id=commande.getIdcmd();
        cb_Produit.setValue(commande.getIdP());
        cb_Client.setValue(commande.getIdCl());
        TfQuantity.setText(Integer.toString(commande.getQuantity()));
        TfMontant.setText(Float.toString(commande.getMontant()));
        date.setValue(commande.getDatecmd().toLocalDate());
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
    private void DeleteCommande(ActionEvent event) {
        int Id;
        Id=CommandeTab.getSelectionModel().getSelectedItem().getIdcmd();
        try {
            cs.supprimer(id);
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 refreshlist();
    }
    
    public void updateReclamation() throws SQLException{
        
        Commande commande = new Commande(cb_Produit.getValue(),
                                                 cb_Client.getValue(), 
                                                 Integer.parseInt(TfQuantity.getText()),
                                                 Float.parseFloat(TfMontant.getText()),
                                                 date.getValue().atStartOfDay());
        cs.modifier(id, commande);
        refreshlist(); 
    }
    @FXML
    private void EditCommande(ActionEvent event) {
            try {
            updateReclamation();
            refreshlist();
            recherche_avance();
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
