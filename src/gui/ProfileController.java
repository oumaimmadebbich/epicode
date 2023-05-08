/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.User;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author habac
 */
public class ProfileController implements Initializable {
     @FXML
    private TextField fld_adr;

    @FXML
    private TextField fld_email;

    @FXML
    private TextField fld_nom;

    @FXML
    private TextField fld_num;

    @FXML
    private TextField fld_prenom;

    @FXML
    private TextField fld_role;

    @FXML
    private Button logout;

    @FXML
    private Button modifier;

    @FXML
    private Button reset_pwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserService us = new UserService();
        int id = us.current_user();
        User user = new User();
        user = us.afficher_user(id);
        System.out.print(user);
        fld_nom.setText(user.getNom());
        fld_num.setText(user.getTel());
        fld_email.setText(user.getEmail());
        String role ="";
        String role_num = user.getRole();
        
        if (role_num.equals("1")){
            role="Client";
        }
        
        else if(role_num.equals("2")){
            role="Livreur";
        }
        fld_role.setText(role);
    }
        @FXML
    private void modifier(ActionEvent event) {
        UserService us = new UserService();
        int id = us.current_user();
        User user = new User();
        user = us.afficher_user(id);
        String role ="";
        if ("Client".equals(fld_role.getText())){
            role="1";
        }
        else if ("Livreur".equals(fld_role.getText())){
            role="2";
        }
        User u = new User(id, fld_nom.getText(), fld_email.getText(), fld_num.getText(), role, user.getPassword());
        us.modifier(u);
    }

    @FXML
    private void log_out(ActionEvent event) throws IOException {

        UserService us = new UserService();
        us.reset_token();
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("login.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void reset_pwd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("reset_pwd.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    
    }    
    

