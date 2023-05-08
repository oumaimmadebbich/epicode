/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import entities.User;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Houssem
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField pass2;
    @FXML
    private TextField pass3;
    
    UserService su = new UserService();
    
    private String email;
    private String telephone;

    public String getemail() {
        return email;
    }

    public void setemail_user(String email) {
        this.email = email;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Reset(ActionEvent event) {
        
          User u=new User();
        u.setPassword(pass2.getText());
        u.setPassword(pass3.getText());
        
    }
    
}
