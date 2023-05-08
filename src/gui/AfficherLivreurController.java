/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.LivraisonService;
import services.LivreurService;
import services.PDFGenerator1;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import entities.Livraison;
import entities.Livreur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_secteur;
    @FXML
    private TextField txt_telephone;
    
    @FXML
private TextField searchField;

    private ListData listdata = new ListData();
    @FXML
    private Button retour;
    @FXML
    private Button modif2;


    @FXML
    private Button btn_delete;
    @FXML
    private Button retourr;
    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField insert_id;
    @FXML
    private TextField insert_Pass;
    @FXML
    private Button profile;

   PDFGenerator1 pdf=new PDFGenerator1();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

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
    private void trierListeLivreur(ObservableList<Livreur> list, String[] attributs) {
    Comparator<Livreur> comparator = (Livreur livraison1, Livreur livraison2) -> {
        for (String attribut : attributs) {
            if (attribut.equals("Nom")) {
                int result = livraison1.getNom().compareTo(livraison2.getNom());
                if (result != 0) {
                    return result;
                }
            } else if (attribut.equals("Prenom")) {
                int result = livraison1.getPrenom().compareTo(livraison2.getPrenom());
                if (result != 0) {
                    return result;
                }
            } else if (attribut.equals("Secteur")) {
                int result = livraison1.getSecteur().compareTo(livraison2.getSecteur());
                if (result != 0) {
                    return result;
                }
            }
        }
        return 0;
    };
    FXCollections.sort(list, comparator);
}


   @FXML
   private void exportpdf(ActionEvent event) {
       if(listviewP.getSelectionModel().getSelectedItem()!= null){
       Livreur O = listviewP.getSelectionModel().getSelectedItem();
        int id=O.getId();
        List<Livraison> list=new ArrayList<>();
        LivraisonService ls=LivraisonService.getInstance();
         list= ls.displayAllList1(id);
            try {
                try {
					pdf.GeneratePdf("Livraison", list);
				}catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            // TODO Auto-generated catch block
            
            } catch (IOException ex) {
                Logger.getLogger(AfficherLivreurController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(AfficherLivreurController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AfficherLivreurController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
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
        Parent root2 = FXMLLoader.load(getClass().getResource("AfficherLivreur.fxml"));
        Stage window = (Stage) retour.getScene().getWindow();
        window.setScene(new Scene(root2));

    }
    @FXML
 private void trouver_profile(ActionEvent event) throws IOException {
    int id = Integer.parseInt(insert_id.getText()); // Convertir la valeur du champ de texte en un entier
    String password = insert_Pass.getText();

    // Créer une nouvelle liste d'observables pour stocker les livreurs correspondants
    ObservableList<Livreur> livreurs = FXCollections.observableArrayList();

    // Parcourir la liste existante et ajouter les livreurs correspondants à la nouvelle liste
    for (Livreur livreur : listdata.getLivreurs()) {
        if (livreur.getId() == id && livreur.getPassword().equals(password)) {
            livreurs.add(livreur);
        }
    }

    // Afficher les livreurs correspondants dans la liste
    listviewP.setItems(livreurs);
}

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root3 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) retourr.getScene().getWindow();
        window.setScene(new Scene(root3));
    }    
    
@FXML
private void searchLivreur() {
    String search = searchField.getText().toLowerCase();
    ObservableList<Livreur> searchResults = FXCollections.observableArrayList();

    for (Livreur livreur : listdata.getLivreurs()) {
        if (livreur.getNom().toLowerCase().contains(search) || 
            livreur.getPrenom().toLowerCase().contains(search) ||
            livreur.getSecteur().toLowerCase().contains(search) ||
            livreur.getTelephone().toLowerCase().contains(search)) {
            searchResults.add(livreur);
        }
    }

    listviewP.setItems(searchResults);
}

}
