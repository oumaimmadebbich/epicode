/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.LivraisonService;
import services.LivreurService;
import entities.Livraison;
import entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
//**********************************

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AfficherLivraisonController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<Livraison> listviewP;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_add;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_cmd;

    private Label typeLabel;

    private  List_Livraison listdata = new List_Livraison();
    @FXML
    private Button retour;
    @FXML
    private Button modif2;


    @FXML
    private Button btn_delete;
    @FXML
    private Button retourr;
        @FXML
private TextField searchField;
    @FXML
    private AnchorPane left_main;
//    @FXML
//    private TextField txt_id;
      public static final String ACCOUNT_SID = "ACf6fdb03518098cd1fd30281ba1a56f13";
  public static final String AUTH_TOKEN = "55cf8e7a55d401a2a297ffe23fd8bee6"; 
    @FXML
    private Button retourr1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Livraison> data = FXCollections.observableArrayList();

        listviewP.setItems(listdata.getLivraisons());

        listviewP.setOnMouseClicked(event -> {

            Livraison current = listviewP.getSelectionModel().getSelectedItem();
            current.getId();
            txt_nom.setText(current.getNom_destination());
            txt_add.setText(current.getAdresse_destination());

            txt_tel.setText(current.getTelephone_destination());


        });

    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        LivraisonService pdao = LivraisonService.getInstance();
        Livraison selectedItem2 = listviewP.getSelectionModel().getSelectedItem();

        listviewP.getItems().remove(selectedItem2);
        pdao.delete(selectedItem2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Livreur suprimée avec succés!");
        alert.show();

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        Livraison current = listviewP.getSelectionModel().getSelectedItem();
        Livraison p = new Livraison();
//            p.setId(Integer.parseInt(txt_id.getText()));
        p.setId(current.getId());
        
        p.setNom_destination(txt_nom.getText());
        p.setAdresse_destination(txt_add.getText());
        p.setTelephone_destination(txt_tel.getText());

        LivraisonService l = LivraisonService.getInstance();
        l.update(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Livraison modifiée avec succés!");
        alert.show();
        txt_nom.setText("");
        txt_add.setText("");
        txt_tel.setText("");
       

    }

    @FXML
    private void rafraichir(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("AfficherLivraison.fxml"));
        Stage window = (Stage) retour.getScene().getWindow();
        window.setScene(new Scene(root2));

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("dash.fxml"));
        Stage window = (Stage) retourr.getScene().getWindow();
        window.setScene(new Scene(root3));
    }

@FXML
private void searchLivraison() {
    String search = searchField.getText().toLowerCase();
    ObservableList<Livraison> searchResults = FXCollections.observableArrayList();

    for (Livraison Livraison : listdata.getLivraisons()) {
        if (Livraison.getAdresse_destination().toLowerCase().contains(search) || 
            Livraison.getNom_destination().toLowerCase().contains(search) ||
            Livraison.getTelephone_destination().toLowerCase().contains(search)) {
            searchResults.add(Livraison);
        }
    }

    listviewP.setItems(searchResults);
}

    @FXML
    private void ajout(ActionEvent event) {
                try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/AjouterLivraison.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}