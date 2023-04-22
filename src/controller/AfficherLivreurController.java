/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.LivreurService;
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
public class AfficherLivreurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<Livreur> listviewP;
    @FXML
    private TableView<Livreur> personsTable;
    @FXML
    private TableColumn<Livreur, String> NomColonne;
    @FXML
    private TableColumn<Livreur, String> PrenomColonne;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_secteur;
    @FXML
    private TextField txt_telephone;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_type;
    @FXML
    private Label idLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label secteurLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label typeLabel;

    private ListData listdata = new ListData();
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
        ObservableList<Livreur> data = FXCollections.observableArrayList();

        listviewP.setItems(listdata.getLivreurs());

        listviewP.setOnMouseClicked(event -> {
//        idLabel.setText(String.valueOf(listdata.getLivreurs()
//                .get(listviewP.getSelectionModel().getSelectedIndex())
//                .getId()));
//        nomLabel.setText(listdata.getLivreurs()
//                .get(listviewP.getSelectionModel().getSelectedIndex())
//                .getNom());
//        prenomLabel.setText(listdata.getLivreurs()
//                .get(listviewP.getSelectionModel().getSelectedIndex())
//                .getPrenom());
            Livreur current = listviewP.getSelectionModel().getSelectedItem();
            // txt_id.setText(Integer.toString(current.getId()));

            current.getId();
            txt_nom.setText(current.getNom());
            txt_prenom.setText(current.getPrenom());
            txt_secteur.setText(current.getSecteur());
            txt_telephone.setText(current.getTelephone());

        });

    }


    @FXML
    private void delete(ActionEvent event) throws IOException {
        LivreurService pdao = LivreurService.getInstance();
        Livreur selectedItem2 = listviewP.getSelectionModel().getSelectedItem();

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
        Livreur current = listviewP.getSelectionModel().getSelectedItem();
        Livreur p = new Livreur();
//            p.setId(Integer.parseInt(txt_id.getText()));
        p.setId(current.getId());
        p.setNom(txt_nom.getText());
        p.setPrenom(txt_prenom.getText());
        p.setTelephone(txt_telephone.getText());
        p.setSecteur(txt_secteur.getText());
        LivreurService l = LivreurService.getInstance();
        l.update(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Livreur modifiée avec succés!");
        alert.show();
        txt_nom.setText("");
        txt_prenom.setText("");
        txt_secteur.setText("");
        txt_telephone.setText("");

    }

    @FXML
    private void rafraichir(ActionEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("/view/AfficherLivreur.fxml"));
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
