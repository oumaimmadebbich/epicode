/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author habac
 */
public class BackendController implements Initializable {

    @FXML
    private Button btn_modif;
    @FXML
    private Button logout;
    @FXML
    private TextField nom;
    @FXML
    private TextField numero;
    @FXML
    private ComboBox<String> role;
    @FXML
    private PasswordField password;
    @FXML
    private TableColumn<User, String> Id;
    @FXML
    private TableColumn<User, String> nomm;
    @FXML
    private TableColumn<User, String> emaill;
    @FXML
    private TableColumn<User, String> passwordd;
    private TableColumn<User, String> numeroo;
    @FXML
    private TableColumn<User, String> rolee;
    @FXML
    private TableView<User> affiche;
    private TableColumn<User, String> telephone;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField email;
    @FXML
    private TableColumn<User, String> telephonee;
    @FXML
    private Button btn_modif1;
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.getItems().add("Client");
        role.getItems().add("Livreur");
        
           UserService us = new UserService();
        
        List<User> l = new ArrayList<>();
        
        l = (ArrayList<User>) us.afficher();
        ObservableList<User> data = FXCollections.observableArrayList(l);
        FilteredList<User> fle = new FilteredList(data, e -> true);
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomm.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emaill.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordd.setCellValueFactory(new PropertyValueFactory<>("password"));
        telephonee.setCellValueFactory(new PropertyValueFactory<>("nom"));
        rolee.setCellValueFactory(new PropertyValueFactory<>("role"));
        affiche.setItems(fle);
        int nbe=affiche.getItems().size();
    }

    private void affichage() throws IOException {
        UserService us = new UserService();
        
        List<User> l = new ArrayList<>();
        
        l = (ArrayList<User>) us.afficher();
        ObservableList<User> data = FXCollections.observableArrayList(l);
        FilteredList<User> fle = new FilteredList(data, e -> true);
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomm.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emaill.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordd.setCellValueFactory(new PropertyValueFactory<>("password"));
        telephonee.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        rolee.setCellValueFactory(new PropertyValueFactory<>("role"));
        affiche.setItems(fle);
        int nbe=affiche.getItems().size();
    }

    
    @FXML
    private void modifiers(ActionEvent event) throws IOException {
        User u = new User();
        UserService su = new UserService();
       // u.setId(Integer.parseInt(id.getText()));
        //u.setEmail(email.getText());
        
        u.setNom(nom.getText());
        u.setPassword(password.getText());
        u.setTel(numero.getText());
        u.setRole(role.getValue());

        su.modifier(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("modification éffectuée");
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/backend.fxml"));
        Parent root = loader.load();
        BackendController aa = loader.getController();
        btn_modif.getScene().setRoot(root);
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        Parent root = loader.load();
        LoginController aa = loader.getController();
        logout.getScene().setRoot(root);
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        
        UserService us = new UserService();
        User u = new User();
          u = affiche.getSelectionModel().getSelectedItem();
        us.supprimer2(u.getId());
        affichage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Done");
        alert.setContentText("User Supprimé Avec Succés!");
        alert.show();
    }

    @FXML
    private void ajouteee() throws IOException {
        
         User u =new User();
            UserService us = new UserService();
            u.setNom(nom.getText());
            u.setEmail(email.getText());
            u.setPassword(password.getText());
            u.setRole(role.getValue());
            u.setTel(telephone.getText());
            
             if (us.emailExists(u.getEmail())) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setHeaderText("Email déjà utilisé");
       alert.setContentText("L'email que vous avez saisi existe déjà dans la base de données.");
       alert.showAndWait();
            } else {
            us.ajouter(u);
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("User Ajoute");
                alert.showAndWait();
                affichage();
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/backend.fxml"));
                Parent root = loader.load();
                LoginController aa = loader.getController();
                btn_ajout.getScene().setRoot(root);

        
             }
    }

    @FXML
    private void retour(ActionEvent event) {
                             try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/dash.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    

}
