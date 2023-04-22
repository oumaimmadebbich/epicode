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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author azizb
 */
public class ModifLivreurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private ComboBox<String> list;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList();
    @FXML
    private Button modifier;

    @FXML
    private Button retour2;

    @FXML
    private TextField txt_nom;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

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
    void getValue(ActionEvent event) {
        try {
            // Connection cnx =LivreurService.getInstance().getCon();
            LivreurService pdao = LivreurService.getInstance();
            List<Livreur> Livreurs = new ArrayList<>();
            Livreur c = new Livreur();
            int value = Integer.parseInt(list.getValue());
            System.out.println(value);
            String req = "select * from livreur where id = " + value;
            Statement cs = cnx.createStatement();
            ResultSet rs = cs.executeQuery(req);
            while (rs.next()) {
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setTelephone(rs.getString("telephone"));
                c.setSecteur(rs.getString("secteur"));
                Livreurs.add(c);
                txt_nom.setText(c.getNom());
                txt_prenom.setText(c.getPrenom());
                txt_secteur.setText(c.getSecteur());
                txt_telephone.setText(c.getTelephone());
            }

//            Iimage.setText(rs.getString("image
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void modifierLivreur(ActionEvent event) {
        Livreur cs = new Livreur();
        int value = Integer.parseInt((String) list.getValue());
        Livreur cg = new Livreur(txt_nom.getText(), txt_prenom.getText(), txt_secteur.getText(), txt_telephone.getText(), txt_password.getText(), txt_type.getText());
        // update(cg);
        //list();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("le Livreur a ete modifié");
        alert.show();
    }

    @FXML
    private void back2(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("/com/esprit/view/AfficherPersonne.fxml"));
        Stage window = (Stage) retour2.getScene().getWindow();
        window.setScene(new Scene(root3));

    }
//      private void list() {
//      grid.getChildren().clear();
//      LivreurService cs= new LivreurService();
//      //affiche.setText(ps.afficher().toString());
//          //affiche.setText(bb.afficher().toString());
//         List<Livreur> Livreurs = cs.afficher();
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < Livreurs.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/GUI/afficherFXML1.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                AfficherFXML1Controller itemController = fxmlLoader.getController();
//                itemController.setData1(Livreurs.get(i));
//
//                if (column == 1) {
//                    column = 0;
//                    row++;
//                }
//
//                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(10));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
