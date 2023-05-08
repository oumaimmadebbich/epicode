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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
public class Sign_upController implements Initializable {
      @FXML
    private Button btn_inscri;

    @FXML
    private Button btn_login;

    private ComboBox<String> cmb_role;

    private TextField fld_adr;

    private TextField fld_email;

    private TextField fld_nom;

    private TextField fld_num;

    private TextField fld_prenom;

    private PasswordField fld_pwd;

    @FXML
    private PasswordField fld_pwd2;
    @FXML
    private TextField telephone;
    @FXML
    private ComboBox<String> role;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         role.getItems().add("Client");
         role.getItems().add("Livreur");
    }    
    
    
     void inscrire(ActionEvent event) throws IOException {
         
         
          
     }
        
      /*  User u =new User(nom.getText(),email.getText(),password.getText(),role.toString(),telephone.getText());
        UserService us=new UserService();
        us.ajouter(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));
        Stage primaryStage=new Stage();
        Parent root = loader.load();
        Scene homescene=new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();

    }*/

    @FXML
    void swap_login(ActionEvent event) throws IOException {
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
                
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/login.fxml"));
                Parent root = loader.load();
                LoginController aa = loader.getController();
                btn_inscri.getScene().setRoot(root);

        
             }
        /*--------------- suppression des champs-------------
        fld_nom.setText("");
        fld_prenom.setText("");
        fld_pwd2.setText("");
        fld_adr.setText("");
        fld_num.setText("");
        -----------------------------------------------
        
        
        */
        //controle de saisie
       /* if (nom.isEmpty()) {
            alert_Box("Verifier votre nom", "Votre nom ne doit pas être vide");
        } else if (!uUtiles.testEmail(mail)) {
            alert_Box("Verifier votre mail", "veillez saisir une adresse mail valide");
        } else if (!password.equals(cpassword)) {
            alert_Box("Verifier mot de passe", "Veillez verifier votre mot de passe ");
        } else if (!uUtiles.testTel(tel)) {
            alert_Box("Verifier votre numero telephone", "Veillez mettre un numero de telephone valide");

        } else {
            String cv = "";
            String photo = "";
            User u = new User(nom, mail,tel,role,uUtiles.crypterPassword(password));
            uService.ajouter(u);
            information_Box("Compte créer avec succès", "bienvenue");
            */
        }
    

    /*public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
       
    }
    public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }*/

    @FXML
    private void login(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


    
}

