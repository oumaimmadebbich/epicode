/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.LivraisonService;
import Service.LivreurService;
import entity.Livraison;
import entity.Livreur;
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
    private TableView<Livraison> personsTable;
    @FXML
    private TableColumn<Livreur, String> NomColonne;
    @FXML
    private TableColumn<Livreur, String> PrenomColonne;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_add;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_cmd;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_type;
    @FXML
    private Label idLabel;

    private Label typeLabel;

    private List_Livraison listdata = new List_Livraison();
    @FXML
    private Button retour;
    @FXML
    private Button btnn;
    @FXML
    private AnchorPane gestionLivreur;
    @FXML
    private Button modif2;

    @FXML
    private Button btn_pie;

    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_afficher;
    @FXML
    private Button retourr;
    @FXML
    private Button modif;
//    @FXML
//    private TextField txt_id;

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

            txt_cmd.setText(current.getId_commande_id());

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
    private void modifier(ActionEvent event) throws IOException {
        Livraison current = listviewP.getSelectionModel().getSelectedItem();
        Livraison p = new Livraison();
//            p.setId(Integer.parseInt(txt_id.getText()));
        p.setId(current.getId());
        p.setNom_destination(txt_nom.getText());
        p.setAdresse_destination(txt_add.getText());
        p.setId_commande_id(txt_tel.getText());
        p.setTelephone_destination(txt_cmd.getText());

        LivraisonService l = LivraisonService.getInstance();
        l.update(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Livreur modifiée avec succés!");
        alert.show();
        txt_nom.setText("");
        txt_add.setText("");
        txt_tel.setText("");
        txt_cmd.setText("");

    }

    @FXML
    private void rafraichir(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/view/AfficherLivraison.fxml"));
        Stage window = (Stage) retour.getScene().getWindow();
        window.setScene(new Scene(root2));

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("/view/AcceuilController2.fxml"));
        Stage window = (Stage) retourr.getScene().getWindow();
        window.setScene(new Scene(root3));
    }

}
