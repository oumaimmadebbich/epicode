/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.UserService;
import utils.UserUtils;

/**
 * FXML Controller class
 *
 * @author habac
 */
public class LoginController implements Initializable {

    @FXML
    private Button btn_inscri;

    @FXML
    private Button btn_login;

    private TextField password_login;

    private TextField username_login;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button oublie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }
 private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
            return password; // En cas d'erreur, retourne le mot de passe non crypté
        }
    }
    @FXML
    private void profil(ActionEvent event) throws IOException {
        UserService us = new UserService();
        User u = new User();

        u.setEmail(email.getText());
        u.setPassword(encryptPassword(password.getText()));
        if (us.Authentification(u)) {
            Parent home_page_parent;

            if (us.checkRole(email.getText()).equals("Admin")) {
                // Session.getFirstInstance(Session.getUser());
                // int ide = Session.getUser().getId();
                //  System.out.println(Session.getUser().getId_user());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Admin Connecté");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dash.fxml"));
                home_page_parent = loader.load();
                DashController da = loader.getController();
                btn_login.getScene().setRoot(home_page_parent);
            }  else if (us.checkRole(email.getText()).equals("Client")) {
                //Session.getFirstInstance(Session.getUser());
                //  System.out.println(Session.getUser().id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Client Connecté");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Menu.fxml"));
                home_page_parent = loader.load();
                MenuController da = loader.getController();
                btn_login.getScene().setRoot(home_page_parent);

            }
            else if (us.checkRole(email.getText()).equals("Livreur")) {
                //Session.getFirstInstance(Session.getUser());
                //  System.out.println(Session.getUser().id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Livreur Connecté");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterLivreur.fxml"));
                home_page_parent = loader.load();
                AjouterLivreurController da = loader.getController();
                btn_login.getScene().setRoot(home_page_parent);

            }

        }
    }

    private void swap_inscri(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sign_up.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    /*  private void login_user(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/profile.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    private void log_in(ActionEvent event) throws IOException {

        UserUtils uUtils = new UserUtils();
        UserService sU = new UserService();
        String username = username_login.getText();
        String password = password_login.getText();
        sU.set_token(username,password);

        String role = sU.verifierData(username, password);
        if (role != "Client" || role !="Livreur") {
            uUtils.alert_Box("Verification", "Veillez verifier vos cordonée");
        } else if (role == "Client") {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("profile.fxml"));
            Stage primaryStage = new Stage();
            Parent root = loader.load();
            Scene homescene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homescene);
            window.show();

        } else if (role == "livreur") {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("profile.fxml"));
            Stage primaryStage = new Stage();
            Parent root = loader.load();
            Scene homescene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homescene);
            window.show();

        
    }
    }
    

    private void login(ActionEvent event) throws IOException {
   UserUtils uUtils = new UserUtils();
        UserService sU = new UserService();
        String username = username_login.getText();
        String password = password_login.getText();
        //sU.set_token(username,password);

        String role = sU.verifierData(username, password);
        System.out.println(role);
        String client ="Client";
        String livrerur ="Livreur";
        if (role != client ) {
            uUtils.alert_Box("Verification", "Veillez verifier vos cordonée");
        } else if (role == "Client") {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("profile.fxml"));
            Stage primaryStage = new Stage();
            Parent root = loader.load();
            Scene homescene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homescene);
            window.show();

        } else if (role == "livreur") {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("profile.fxml"));
            Stage primaryStage = new Stage();
            Parent root = loader.load();
            Scene homescene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homescene);
            window.show();

        
    }

    } */
    @FXML
    private void oublie(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/ChoseReset.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void inscrit(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Sign_up.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
