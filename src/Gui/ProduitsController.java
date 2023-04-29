/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entites.Produit;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author oumaima debbich
 */
public class ProduitsController implements Initializable {

    int idProd;
    @FXML
    private ImageView imageview;
   
    @FXML
    private Label nomLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label nbLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void go_cod(MouseEvent event) {
    }
    
    private Produit eve=new Produit();
    
    public void setProduit(Produit e) {
        this.eve=e;
        nomLabel.setText(e.getTitre());
        typeLabel.setText(String.valueOf(e.getPrix()));
        descriptionLabel.setText(e.getDescription());
        nbLabel.setText(String.valueOf(e.getQuantite()));
         String path = e.getImage();
         File file=new File(path);
         Image img = new Image(file.toURI().toString());
         imageview.setImage(img);

    }
    public void setidProd(int idProd){
        this.idProd=idProd;
    }
    


}
