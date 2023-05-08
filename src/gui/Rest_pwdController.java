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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.User;
import services.UserService;
import utils.UserUtils;

/**
 * FXML Controller class
 *
 * @author habac
 */
public class Rest_pwdController implements Initializable {
     @FXML
    private PasswordField a_pwd;

    @FXML
    private Button annuler;

    @FXML
    private Button logout;

    @FXML
    private TextField nv_pwd;

    @FXML
    private PasswordField nv_pwd1;

    @FXML
    private Button valider;

  

   

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void annuler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("profile.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void reset_pwd(ActionEvent event) {
        UserUtils uUtils = new UserUtils();
        UserService us = new UserService();
        int id = us.current_user();
        User user = new User();
        user = us.afficher_user(id);
        String pwd_a = user.getPassword();
        if ("".equals(a_pwd.getText()) |"".equals(nv_pwd.getText()) ||"".equals(nv_pwd1.getText()) ){
            uUtils.alert_Box("Verification", "Veillez verifier que les champs ne sont pas vide");
        }
        else if(!a_pwd.getText().equals(pwd_a)){
            uUtils.alert_Box("Verification", "Veillez verifier votre mot de passe");
        }
        else if(!nv_pwd.getText().equals(nv_pwd1.getText())){
            uUtils.alert_Box("Verification", "Veillez verifier que les mots de passe sont identiques");
        }
        else
        {
            user.setPassword(nv_pwd.getText());
            us.modifier(user);
        }
        
        
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
    
    
}
